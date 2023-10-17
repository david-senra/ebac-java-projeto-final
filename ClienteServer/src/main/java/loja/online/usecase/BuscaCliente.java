
package loja.online.usecase;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import loja.online.domain.Cliente;
import loja.online.exception.EntityNotFoundException;
import loja.online.repository.IClienteRepository;

// Classe de "tipo de método" para o Resources, que agrupa conjunto de métodos.

@Service //Indica que se trata de um sub serviço
public class BuscaCliente {

	private IClienteRepository clienteRepository;
	
	public BuscaCliente(IClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public Page<Cliente> buscar(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}
	
	public Cliente buscarPorId(String id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Cliente.class, "id", id));
	}
	
	public Boolean isCadastrado(String id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.isPresent() ? true : false;
	}

	public Cliente buscarPorCpf(Long cpf) {
		return clienteRepository.findByCpf(cpf)
				.orElseThrow(() -> new EntityNotFoundException(Cliente.class, "cpf", String.valueOf(cpf)));
	}

	
}
