package loja.online.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

// Configuração das informações mostradas na página principal do OpenAPI/Swagger deste serviço.

@Configuration
public class OpenAPIConfig {

	@Bean
	OpenAPI customOpenAPI(@Value("${application-version}") String appVersion) {
		return new OpenAPI()
				          .info(new Info()
				          .title("Serviço de Vendas")
				          .version(appVersion)
				          .description("Serviço para gerenciamento de Vendas")
				          .termsOfService("http://swagger.io/terms/")
				          .license(new License().name("Apache 2.0").url("http://springdoc.org"))
				          .contact(new Contact().name("David Senra").email("davidsenra33@gmail.com")));
	}
}
