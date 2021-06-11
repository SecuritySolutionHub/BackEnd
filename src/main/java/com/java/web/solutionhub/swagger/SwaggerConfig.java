package com.java.web.solutionhub.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("solution_hub member")
				.description("solution_hub member API")
				.build();
	}
	
	@Bean
	public Docket commonApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("solution_hub")
				.apiInfo(this.apiInfo())
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("com.java.web.solutionhub.member.controller"))
				.paths(PathSelectors.ant("/**"))
				.build();
	}
}
