package loja.online.config;

import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

// Arquivo de configuração para habilitar validação nos campos do banco de dados.

@Configuration
public class ValidatorConfig {
	
//	@Autowired
//    ResourceBundleMessageSource messageSource;

	@Bean
    Validator validatorFactory () {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//	    bean.setValidationMessageSource(messageSource);
	    return bean;
    }
}
