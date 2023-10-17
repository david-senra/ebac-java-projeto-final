package loja.online.domain;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "cliente") // Amarra com o banco de dados
@Getter // uso do Lombok
@Setter // uso do Lombok
@AllArgsConstructor  // uso do Lombok
@NoArgsConstructor  // uso do Lombok
@Builder  // uso do Lombok
@Schema(name="Cliente", description="Cliente") //Definição no OpenAPI/Swagger
public class Cliente {
	
	@Id //Define como identificador único
	@Schema(description="Identificador único") //Descrição no OpenAPI/Swagger
	private String id;
	
	@NotNull //Define como obrigatório
	@Size(min = 1, max = 50) //Definição dos tamanhos aceitos
	@Schema(description="Nome", minLength = 1, maxLength=20, nullable = false) 
	private String nome;
	
	@NotNull //Define como obrigatório
	@Size(min = 1, max = 50) //Definição dos tamanhos aceitos
	@Schema(description="Sobrenome", minLength = 1, maxLength=50, nullable = false) 
	private String sobrenome;
	
	@NotNull //Define como obrigatório
	@Indexed(unique = true, background = true)
	@Schema(description="CPF", nullable = false) 
    private Long cpf;
    
	@NotNull //Define como obrigatório
	@Schema(description="Telefone", nullable = false) 
    private Long tel;
	
	@NotNull //Define como obrigatório
	@Size(min = 1, max = 50) //Definição dos tamanhos aceitos
	@Indexed(unique = true, background = true) //Definindo que o campo não pode repetir para outro elemento
	@Schema(description="Email", minLength = 1, maxLength=50, nullable = false)
	@Pattern(regexp = ".+@.+\\..+", message = "Email inválido") //Definição de condição
	private String email;
    
	@NotNull //Define como obrigatório
	@Size(min = 1, max = 50) //Definição dos tamanhos aceitos
	@Schema(description="Endereço", minLength = 1, maxLength=50, nullable = false)
    private String end;
    
	@NotNull //Define como obrigatório
	@Schema(description="Numero residencial", nullable = false) 
    private Integer numero;
    
	@NotNull //Define como obrigatório
	@Size(min = 1, max = 50) //Definição dos tamanhos aceitos
	@Schema(description="Cidade", minLength = 1, maxLength=50, nullable = false)
    private String cidade;
    
	@NotNull //Define como obrigatório
	@Size(min = 1, max = 50) //Definição dos tamanhos aceitos
	@Schema(description="Estado", minLength = 1, maxLength=50, nullable = false)
    private String estado;
    

}
