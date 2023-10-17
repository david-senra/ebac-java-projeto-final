package loja.online.usecase;

import java.math.BigDecimal;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import loja.online.domain.Produto;
import loja.online.domain.Venda;
import loja.online.domain.Venda.Status;
import loja.online.dto.VendaDTO;
import loja.online.exception.EntityNotFoundException;
import loja.online.repository.IVendaRepository;
import loja.online.service.ClienteService;
import loja.online.service.IProdutoService;

// Similar ao dos outros, mas incorporando produto e clientes por duas formas diferentes (ver classes)

@Service
public class CadastroVenda {
	
	private IVendaRepository vendaRepository;
	
	private IProdutoService produtoService;
	
	private ClienteService clienteService;
	
	public CadastroVenda(IVendaRepository vendaRepository,
			IProdutoService produtoService,
			ClienteService clienteService) {
		this.vendaRepository = vendaRepository;
		this.produtoService = produtoService;
		this.clienteService = clienteService;
	}
	
	public Venda cadastrar(@Valid VendaDTO vendaDTO) {
		Venda venda = convertToDomain(vendaDTO, Status.INICIADA);
		validarCliente(venda.getClienteId());
		venda.recalcularValorTotalVenda();
		return this.vendaRepository.insert(venda);
	}

	private void validarCliente(String clienteId) {
		Boolean isCadastrado = 
				this.clienteService.isClienteCadastrado(clienteId);
		if (!isCadastrado) {
			new EntityNotFoundException(Venda.class, "clienteId", clienteId);
		}
	}

	private Venda convertToDomain(@Valid VendaDTO vendaDTO, Status status) {
		return Venda.builder()
				.clienteId(vendaDTO.getClienteId())
				.codigo(vendaDTO.getCodigo())
				.dataVenda(vendaDTO.getDataVenda())
				.status(status)
				.valorTotal(BigDecimal.ZERO)
				.produtos(new HashSet<>())
				.build();
	}

	public Venda atualizar(@Valid Venda venda) {
		return this.vendaRepository.save(venda);
	}

	public Venda finalizar(String id) {
		Venda venda = buscarVenda(id);
		venda.validarStatus();
		venda.setStatus(Status.CONCLUIDA);
		return this.vendaRepository.save(venda);
	}
	
	public Venda cancelar(String id) {
		Venda venda = buscarVenda(id);
		venda.validarStatus();
		venda.setStatus(Status.CANCELADA);
		return this.vendaRepository.save(venda);
	}

	public Venda adicionarProduto(String id, String codigoProduto, Integer quantidade) {
		Venda venda = buscarVenda(id);
		Produto produto = buscarProduto(codigoProduto);
		venda.validarStatus();
		venda.adicionarProduto(produto, quantidade);
		return this.vendaRepository.save(venda);
	}
	
	public Venda removerProduto(String id, String codigoProduto, Integer quantidade) {
		Venda venda = buscarVenda(id);
		Produto produto = buscarProduto(codigoProduto);
		venda.validarStatus();
		venda.removerProduto(produto, quantidade);
		return this.vendaRepository.save(venda);
	}
	
	private Venda buscarVenda(String id) {
		return vendaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Venda.class, "id", id));
		
	}
	
	private Produto buscarProduto(String codigoProduto) {
		Produto prod = produtoService.buscarProduto(codigoProduto);
		if (prod == null) {
			throw new EntityNotFoundException(Produto.class, "codigo", codigoProduto);
		}
		return prod;
	}
	
	public void removerPorId(String id) {
		Venda venda = vendaRepository.findById(id)
		.orElseThrow(() -> new EntityNotFoundException(Venda.class, "id", id));
		this.vendaRepository.deleteById(venda.getId());
	}

}
