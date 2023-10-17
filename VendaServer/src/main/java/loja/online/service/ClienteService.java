
package loja.online.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

// Monta uma url para enviar uma request ao servidor do Cliente e verifica se um cliente está cadastrado
// Faz isso por meio de uma string com a ID do cliente, que é recebida como parâmetro no método
// Faz uso de duas classes construídas de forma customizada (ver): RestRequest e RestUtils

@Service
public class ClienteService {

	@Value("${application.clienteService.endpointConsultarCliente}")
	private String urlEndpointConsultarCliente;
	
	private RestUtils restUtils;
	
	public ClienteService(RestUtils restUtils) {
		this.restUtils = restUtils;
	}

	public Boolean isClienteCadastrado(String clienteId) {
		RestRequest restRequest = new RestRequest(HttpMethod.GET, null);
		restRequest.setContentType(MediaType.APPLICATION_JSON);
		restRequest.setAcceptable(Collections.singletonList(MediaType.APPLICATION_JSON));
		String urlComParam = urlEndpointConsultarCliente.replace("{id}", clienteId);
		ResponseEntity<Boolean> response = restUtils.execute(urlComParam, restRequest, Boolean.class);
		return response.getBody();
	}

}
