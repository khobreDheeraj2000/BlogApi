package com.blogapi.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@OpenAPIDefinition

public class SwaggerConfig {
	@Bean
	public OpenAPI baseOpenAPI() {
		return new OpenAPI()
				.components(new Components().addSecuritySchemes("JWT",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat(null)))
				.info(new Info().title("Blogging API").version("2.0.4").description("Spring doc"))
				.addSecurityItem(new SecurityRequirement().addList("JWT", Arrays.asList("read", "write")));
	}
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).select()
//				.paths(PathSelectors.any()).apis(RequestHandlerSelectors.any()).build();
//	}
//	
//	private ApiInfo getInfo() {
//		return new ApiInfo("Blogging Application : Backend Course", "This project is developed by Dheeraj", "1.0","Terms of Service", new Contact("Dheeraj","abcd","dheeraj@mail"), "License of API", "API licence URL", Collections.emptyList());
//	}
}
