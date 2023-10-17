
package loja.online.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import loja.online.domain.Produto;

// Similar ao serviço do cliente, mas por outra técnica.
// Cria uma URL para acessar o serviço dos produtos e buscar um produto
// O único método "buscarProduto" recebe como parâmetro o código do Produto para a busca.

@FeignClient(name = "produto", url = "${application.produtoService.endpointConsultarProduto}")
public interface IProdutoService {

	@RequestMapping(method = RequestMethod.GET, value = "/{codigo}", produces = "application/json", headers = "application/json")
	Produto buscarProduto(@RequestParam("codigo") String codigoProduto);

}
