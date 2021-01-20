package com.mavenir.mbmp.abcservicesimulator;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.mavenir.mbmp.abcservicesimulator.utils.constants.ABCRequestHandlerConstants;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements ABCRequestHandlerConstants {

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(apiInfo()).select()
				.paths(postPaths()).build();
	}

	private Predicate<String> postPaths() {
		return regex(MESSAGE_REST_CALL + DOT_STAR);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Campaign Service API")
				.description("Campaign Microservice System API reference for developers")
				.termsOfServiceUrl("http://mavenir.com/").license("Mavenir License")
				.licenseUrl("http://mavenir.com/developer/license").version("1.0").build();
	}

}