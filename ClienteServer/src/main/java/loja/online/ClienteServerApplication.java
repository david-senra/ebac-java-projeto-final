package loja.online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;

// O application.properties define o nome do serviço, que vai bater no ConfigServer e obedecer o indicado lá.

@SpringBootApplication
@RefreshScope //Atualiza automaticamente quando alguma configuração for modificada (não está funcionando)
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) // O "exclude" aí é por causa do ServerConfig. 
public class ClienteServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteServerApplication.class, args);
	}

}
