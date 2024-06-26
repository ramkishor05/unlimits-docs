package com.brijframework.docs.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

	private static final String AUTHORIZATION = "Authorization";
	private final String moduleName = "Docs";
	private final String apiVersion = "1.1";

	@Value("${openapi.service.url}")
	private String serverUrl;
	
	@Bean
	public OpenAPI customOpenAPI() {
		final String securitySchemeName = AUTHORIZATION;
		final String apiTitle = String.format("%s API", StringUtils.capitalize(moduleName));

		return new OpenAPI().addServersItem(new Server().url(serverUrl))
				.components(new Components().addSecuritySchemes(securitySchemeName,
						new SecurityScheme().name(securitySchemeName).type(SecurityScheme.Type.HTTP).scheme("bearer")
								.bearerFormat("JWT")))
				.info(new Info().title(apiTitle).version(apiVersion));
	}
	
	@Bean
    public CorsFilter corsFilter() {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

            CorsConfiguration config = new CorsConfiguration();
            config.setAllowCredentials(true);
            config.addAllowedOrigin("*");
            config.addAllowedHeader("*");
            config.addAllowedMethod("*");

            source.registerCorsConfiguration("/v3/api-docs", config);
            return new CorsFilter(source);
    }

}
