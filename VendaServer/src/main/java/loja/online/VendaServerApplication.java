package loja.online;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@RefreshScope 
@EnableFeignClients // Usa a dependência "open feign" do pom.xml para acessar os outros microsserviços
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class VendaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendaServerApplication.class, args);
	}

}
