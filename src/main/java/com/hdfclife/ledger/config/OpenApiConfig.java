package com.hdfclife.ledger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI ledgerOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("HDFC Life Policy Ledger API")
                        .description("REST API for managing life insurance policies, riders, and claims.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("HDFC Life Ledger Desk")
                                .email("ledger-desk@hdfclife.example")));
    }
}
