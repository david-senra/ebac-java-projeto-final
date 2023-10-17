package loja.online.resources;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import loja.online.domain.Cliente;
import loja.online.usecase.BuscaCliente;
import loja.online.usecase.CadastroCliente;
import io.swagger.v3.oas.annotations.Operation;

// Essa Classe cria os métodos que serão exibidos no Swagger

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {
	
	private BuscaCliente buscaCliente;
	private CadastroCliente cadastroCliente;
	
	public ClienteResource(BuscaCliente buscaCliente, 
			CadastroCliente cadastroCliente) {
		this.buscaCliente = buscaCliente;
		this.cadastroCliente = cadastroCliente;
	}
	
	@GetMapping
	@Operation(summary = "Exibe os clientes")
	public ResponseEntity<Page<Cliente>> buscar(Pageable pageable) {
		return ResponseEntity.ok(buscaCliente.buscar(pageable));
	}
	
	@PutMapping
	@Operation(summary = "Atualiza um cliente")
	public ResponseEntity<Cliente> atualizar(@RequestBody @Valid Cliente cliente) {
		return ResponseEntity.ok(cadastroCliente.atualizar(cliente));
	}
	
	@PostMapping
	@Operation(summary = "Cadastra um cliente")
	public ResponseEntity<Cliente> cadastar(@RequestBody @Valid Cliente cliente) {
		return ResponseEntity.ok(cadastroCliente.cadastrar(cliente));
	}
	
	@GetMapping(value = "/{id}")
	@Operation(summary = "Busca um cliente pelo ID")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable(value = "id", required = true) String id) {
		return ResponseEntity.ok(buscaCliente.buscarPorId(id));
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Remove um cliente pelo ID")
	public ResponseEntity<String> removerPorId(@PathVariable(value = "id") String id) {
		cadastroCliente.removerPorId(id);
		return ResponseEntity.ok("Removido com sucesso");
	}
	
	@GetMapping(value = "isCadastrado/{id}")
	@Operation(summary = "Retorna se há cliente cadastrado com o ID inserido (true ou false)")
	public ResponseEntity<Boolean> isCadastrado(@PathVariable(value = "id", required = true) String id) {
		return ResponseEntity.ok(buscaCliente.isCadastrado(id));
	}
	
	@GetMapping(value = "/cpf/{cpf}")
	@Operation(summary = "Busca um cliente pelo CPF")
	public ResponseEntity<Cliente> buscarPorCpf(@PathVariable(value = "cpf", required = true) Long cpf) {
		return ResponseEntity.ok(buscaCliente.buscarPorCpf(cpf));
	}
	
	@DeleteMapping(value = "/cpf/{cpf}")
	@Operation(summary = "Remove um cliente pelo CPF")
	public ResponseEntity<String> removerPorCpf(@PathVariable(value = "cpf") Long cpf) {
		cadastroCliente.removerPorCpf(cpf);
		return ResponseEntity.ok("Removido com sucesso");
	}
}
