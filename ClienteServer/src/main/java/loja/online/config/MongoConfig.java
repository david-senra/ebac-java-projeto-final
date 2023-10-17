package loja.online.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

// Informações do Dao/Repositório que será utilizado para manipular (CRUD) os valores no Banco de Dados Mongo.

@Configuration
@EnableMongoRepositories(basePackages = "loja.online.repository")
public class MongoConfig {

}
