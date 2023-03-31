package com.blogapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
@Configuration
@OpenAPIDefinition

public class SwaggerConfig {
	@Bean
	public OpenAPI baseOpenAPI() {
	return new  OpenAPI().info(new Info().title("Blogging APi").version("2.0.4").description("spring doc"));
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
