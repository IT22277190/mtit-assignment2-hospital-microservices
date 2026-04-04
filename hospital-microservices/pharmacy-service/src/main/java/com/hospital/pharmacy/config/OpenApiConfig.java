package com.hospital.pharmacy.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI pharmacyServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pharmacy Service API")
                        .version("1.0.0")
                        .description("REST API for Pharmacy/Medicine Management in MediCore Hospital System")
                        .contact(new Contact()
                                .name("MediCore Development Team")
                                .url("http://localhost:8080")))
                .servers(List.of(
                        new Server().url("http://localhost:8084").description("Pharmacy Service (Dev)"),
                        new Server().url("http://pharmacy-service:8084").description("Pharmacy Service (Docker)")
                ));
    }
}
