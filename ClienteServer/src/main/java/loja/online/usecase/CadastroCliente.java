
package loja.online.usecase;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import loja.online.domain.Cliente;
import loja.online.exception.EntityNotFoundException;
import loja.online.repository.IClienteRepository;

//Classe de "tipo de método" para o Resources, que agrupa conjunto de métodos.

@Service //Indica que se trata de um sub serviço
public class CadastroCliente {
	
	private IClienteRepository clienteRepository;

	public CadastroCliente(IClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public Cliente cadastrar(@Valid Cliente cliente) {
		return this.clienteRepository.insert(cliente);
	}

	public Cliente atualizar(@Valid Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}

	public void removerPorId(String id) {
		Cliente cliente = clienteRepository.findById(id)
		.orElseThrow(() -> new EntityNotFoundException(Cliente.class, "id", id));
		this.clienteRepository.deleteById(cliente.getId());
	}

	public void removerPorCpf(Long cpf) {
		Cliente cliente = clienteRepository.findByCpf(cpf)
		.orElseThrow(() -> new EntityNotFoundException(Cliente.class, "cpf", String.valueOf(cpf)));
		this.clienteRepository.deleteByCpf(cliente.getCpf());
	}

}
