package loja.online.usecase;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import loja.online.domain.Produto;
import loja.online.domain.Produto.Status;
import loja.online.exception.EntityNotFoundException;
import loja.online.repository.IProdutoRepository;

@Service
public class CadastroProduto {
	
	private IProdutoRepository produtoRepository;
	
	public CadastroProduto(IProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	public Produto cadastrar(@Valid Produto produto) {
		produto.setStatus(Status.ATIVO);
		return this.produtoRepository.insert(produto);
	}

	public Produto atualizar(@Valid Produto produto) {
		return this.produtoRepository.save(produto);
	}

	public void remover(String id) {
		Produto prod = produtoRepository.findById(id)
		.orElseThrow(() -> new EntityNotFoundException(Produto.class, "id", id));
		prod.setStatus(Status.INATIVO);
		this.produtoRepository.save(prod);
		//this.produtoRepository.deleteById(id);
	}
	
	public void removerSistema(String id) {
		Produto prod = produtoRepository.findById(id)
		.orElseThrow(() -> new EntityNotFoundException(Produto.class, "id", id));
		this.produtoRepository.deleteById(prod.getId());
	}

}
