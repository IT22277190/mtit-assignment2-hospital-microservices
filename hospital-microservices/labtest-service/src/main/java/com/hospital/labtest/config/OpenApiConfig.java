package com.hospital.labtest.config;

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
    public OpenAPI labTestServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Lab Test Service API")
                        .version("1.0.0")
                        .description("REST API for Lab Test Management in MediCore Hospital System")
                        .contact(new Contact()
                                .name("MediCore Development Team")
                                .url("http://localhost:8080")))
                .servers(List.of(
                        new Server().url("http://localhost:8086").description("Lab Test Service (Dev)"),
                        new Server().url("http://labtest-service:8086").description("Lab Test Service (Docker)")
                ));
    }
}
