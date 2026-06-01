package com.api.infra.springDoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * Configuration for OpenAPI (Swagger) documentation.
 * Enables JWT Bearer Token authentication within the Swagger UI.
 */
@Configuration
public class SpringDocConfigurations {

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .components(new Components()
                                                .addSecuritySchemes("bearer-key",
                                                                new SecurityScheme()
                                                                                .type(SecurityScheme.Type.HTTP)
                                                                                .scheme("bearer")
                                                                                .bearerFormat("JWT")))
                                .info(new Info()
                                                .title("API ReciclaPro")
                                                .description("API REST para gestão da clínica ReciclaPro, contendo funcionalidades de médicos, pacientes e agendamento de consultas.")
                                                .version("1.0"));
        }
}