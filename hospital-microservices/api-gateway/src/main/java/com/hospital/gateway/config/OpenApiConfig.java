package com.hospital.gateway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI hospitalOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MediCore Hospital Management API")
                        .version("1.0.0")
                        .description("REST API for MediCore Hospital Management System. " +
                                "All endpoints are accessed through the API Gateway at http://localhost:8080/gateway")
                        .contact(new Contact()
                                .name("MediCore Development Team")
                                .url("http://localhost:8080"))
                        .license(new License()
                                .name("MIT License")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("API Gateway (Development)"),
                        new Server().url("http://localhost:8080/gateway").description("Gateway Base Path")
                ))
                .tags(Arrays.asList(
                        new Tag().name("Patient Service").description("Patient registration and management"),
                        new Tag().name("Doctor Service").description("Doctor profiles and availability"),
                        new Tag().name("Appointment Service").description("Appointment scheduling and management"),
                        new Tag().name("Pharmacy Service").description("Medicine inventory and dispensing"),
                        new Tag().name("Billing Service").description("Invoice generation and payment processing"),
                        new Tag().name("Lab Test Service").description("Laboratory test ordering and results")
                ));
    }
}
