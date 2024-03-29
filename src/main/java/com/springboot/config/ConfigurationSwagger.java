package com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class ConfigurationSwagger {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.springboot"))
				.paths(PathSelectors.any())
				.build().apiInfo(apiInfo());
		//http://192.168.1.200:8089/SpringProject/swagger-ui/index.html
	}
	private ApiInfo apiInfo () {
		return new ApiInfoBuilder()
		.title("Swagger Configuration")
		.description("\"Spring Boot Swagger configuration\"")
		.version("1.1.0").build();
	}
}
