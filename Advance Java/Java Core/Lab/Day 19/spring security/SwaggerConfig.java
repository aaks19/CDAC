package com.healthcare.security;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic")

@OpenAPIDefinition(info = @Info(title = "Healthcare API", version = "v1.0"), security = {

		@SecurityRequirement(name = "basicAuth") })
@Configuration
public class SwaggerConfig {
}
