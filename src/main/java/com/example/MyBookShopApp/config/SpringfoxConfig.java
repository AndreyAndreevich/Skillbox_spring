package com.example.MyBookShopApp.config;

import java.util.ArrayList;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringfoxConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
//            .apis(RequestHandlerSelectors.basePackage("com.example.MyBookShopApp.controllers"))
            .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
            .paths(PathSelectors.any())
//            .paths(PathSelectors.ant("/api/*"))
            .build()
            .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo() {
        return new ApiInfo(
            "Bookshop API",
            "API for bookstore",
            "1.0",
            "http://www.termsofservice.org",
            new Contact(
                "API owner",
                "http://ownersite.com",
                "owner@mailer.com"
            ),
            "api_license",
            "http://license.edu.org",
            new ArrayList<>()
        );
    }
}
