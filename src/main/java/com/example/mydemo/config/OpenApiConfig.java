package com.example.mydemo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                description = "OpenApi documentation for MyStore application",
                title = "MyStore API",
                version = "1.0",
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "MyStore API server",
                        url = "http://localhost:8080"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "Bearer Authentication"
                )
        }
)
@SecurityScheme(
        name = "Bearer Authentication",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
