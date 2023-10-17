
package loja.online.dto;

import java.time.Instant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

// Um construtor de dados para gerar código, ID, e uma data para cadastro de vendas.

@Getter
@Setter
@AllArgsConstructor
@Builder
public class VendaDTO {
	
	@NotNull
	@Size(min = 1, max = 10)
	private String codigo;
	
	@NotNull
	private String clienteId;
	
	@NotNull
	private Instant dataVenda;

}
