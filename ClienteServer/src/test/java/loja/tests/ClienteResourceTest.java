
package loja.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import loja.online.domain.Cliente;
import loja.online.resources.ClienteResource;
import loja.online.usecase.BuscaCliente;
import loja.online.usecase.CadastroCliente;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClienteResourceTest {
	
	@InjectMocks
	private ClienteResource clienteResource;
	
	@MockBean
	private BuscaCliente buscaCliente;
	
	@MockBean
	private CadastroCliente cadastroCliente;
	
	@BeforeEach
    public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void buscarPorIdTest() {
		Cliente cliente1 = Cliente.builder().id("1").nome("Rodrigo").build();
		 
        when(buscaCliente.buscarPorId("1")).thenReturn(cliente1);
 
        ResponseEntity<Cliente> result = clienteResource.buscarPorId("1");
 
        Cliente clienteResult = result.getBody();
        assertThat(clienteResult).isEqualTo(cliente1);
        assertThat(clienteResult.getId()).isEqualTo(cliente1.getId());
        assertThat(clienteResult.getNome()).isEqualTo(cliente1.getNome());
	}

}
