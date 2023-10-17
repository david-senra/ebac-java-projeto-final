
package loja.online.resources;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import loja.online.domain.Venda;
import loja.online.dto.VendaDTO;
import loja.online.usecase.BuscaVenda;
import loja.online.usecase.CadastroVenda;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

// Construção do swagger e das respostas do servidor

@RestController
@RequestMapping(value = "/venda")
public class VendasResources {

	private BuscaVenda buscaVenda;
	
	private CadastroVenda cadastroVenda;
	
	public VendasResources(BuscaVenda buscaVenda,
			CadastroVenda cadastroVenda) {
		this.buscaVenda = buscaVenda;
		this.cadastroVenda = cadastroVenda;
	}
	
	@GetMapping
	@Operation(summary = "Lista as vendas cadastradas")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Retorna a lista de vendas"),
		    @ApiResponse(responseCode = "400", description = "Requisição malformada ou erro de sintaxe", 
		    		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "BAD_REQUEST"))),
		    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção",
		    		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples = @ExampleObject(value = "INTERNAL_SERVER_ERROR"))),
		})
	public ResponseEntity<Page<Venda>> buscar(Pageable pageable) {
		return ResponseEntity.ok(buscaVenda.buscar(pageable));
	}
	
	@GetMapping(value = "/{codigo}")
	@Operation(summary = "Busca uma venda pelo código")
	public ResponseEntity<Venda> buscarPorCodigo(String codigo) {
		return ResponseEntity.ok(buscaVenda.buscarPorCodigo(codigo));
	}
	
	@PostMapping
	@Operation(summary = "Cadastrar uma nova venda")
	public ResponseEntity<Venda> cadastrar(@RequestBody @Valid VendaDTO venda) {
		return ResponseEntity.ok(cadastroVenda.cadastrar(venda));
	}
	
	@PutMapping
	@Operation(summary = "Atualizar venda")
	public ResponseEntity<Venda> atualizar(@RequestBody @Valid Venda venda) {
		return ResponseEntity.ok(cadastroVenda.atualizar(venda));
	}
	
	@PutMapping("/{id}/{codigoProduto}/{quantidadeProduto}/adicionarProduto")
	@Operation(summary = "Adiciona produto(s) a uma venda")
	public ResponseEntity<Venda> adicionarProduto(
			@PathVariable(name = "id", required = true) String id,
			@PathVariable(name = "codigoProduto", required = true) String codigoProduto,
			@PathVariable(name = "quantidadeProduto", required = true) Integer quantidadeProduto) {
		return ResponseEntity.ok(cadastroVenda.adicionarProduto(id, codigoProduto, quantidadeProduto));
	}
	
	@PutMapping("/{id}/{codigoProduto}/{quantidadeProduto}/removerProduto")
	@Operation(summary = "Remove produto(s) de uma venda")
	public ResponseEntity<Venda> removerProduto(
			@PathVariable(name = "id", required = true) String id,
			@PathVariable(name = "codigoProduto", required = true) String codigoProduto,
			@PathVariable(name = "quantidadeProduto", required = true) Integer quantidadeProduto) {
		return ResponseEntity.ok(cadastroVenda.removerProduto(id, codigoProduto, quantidadeProduto));
	}
	
	@PutMapping("/{id}/finalizar")
	@Operation(summary = "Finalizar uma venda pelo ID")
	public ResponseEntity<Venda> finalizar(
			@PathVariable(name = "id", required = true) String id) {
		return ResponseEntity.ok(cadastroVenda.finalizar(id));
	}
	
	@PutMapping("/{id}/cancelar")
	@Operation(summary = "Cancelar uma venda pelo ID")
	public ResponseEntity<Venda> cancelar(
			@PathVariable(name = "id", required = true) String id) {
		return ResponseEntity.ok(cadastroVenda.cancelar(id));
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Remove uma venda pelo ID")
	public ResponseEntity<String> removerPorId(@PathVariable(value = "id") String id) {
		cadastroVenda.removerPorId(id);
		return ResponseEntity.ok("Removido com sucesso");
	}
}
