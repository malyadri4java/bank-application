package com.dailycode.bankapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
public class DocumentConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.dailycode.bankapp"))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Bank API Application",
                "Sample API for application",
                "v1.0",
                "Free to use",
                new Contact("malya", "http://localhost:8982892", "a@a.com"),
                "API Licence",
                "Http:// url",
                Collections.emptyList()
        );
    }
}
