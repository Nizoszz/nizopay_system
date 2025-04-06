package com.system.nizopay.docs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerDocsConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                              .title("NizoPay System API")
                              .version("1.0.0")
                              .description("Documentação da API do sistema NizoPay")
                              .license(new License()
                                               .name("Apache 2.0")
                                               .url("http://springdoc.org"))
                );
    }
}
