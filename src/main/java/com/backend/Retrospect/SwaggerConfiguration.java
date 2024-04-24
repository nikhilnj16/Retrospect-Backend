package com.backend.Retrospect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("User")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.backend.Retrospect.controller.user"))
                .paths(PathSelectors.any())
                .build();
    }

}