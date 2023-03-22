package spring.bank.api_bank.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SpringBank API")
                        .description("API Rest da aplicação SpringBank, contendo as funcionalidades de CRUD de clientes, além das operações saque, depósito e transferência")
                        .contact(new Contact()
                                .name("Time Backend")
                                .email("allasassis10@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://spring.bank/api/licenca")));

    }
}
