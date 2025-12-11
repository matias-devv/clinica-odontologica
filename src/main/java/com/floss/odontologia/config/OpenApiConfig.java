package com.floss.odontologia.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "API de Usuarios",
                version = "1.0",
                description = "API REST para gestión de usuarios"
        )
)
@Configuration
public class OpenApiConfig {
}
