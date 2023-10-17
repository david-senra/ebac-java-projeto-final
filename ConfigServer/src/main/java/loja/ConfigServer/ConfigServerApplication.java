package loja.ConfigServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

// Aplicação responsável por centralizar todos serviços do sistema, configurando cada um separadamente.
// Vai definir características tipo porta utilizada, banco de dados conectado, etc...
// No "application.properties" estão as configurações principais.
// Na pasta "config" paralela, as configurações para cada um dos demais serviços/servidores.

@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
