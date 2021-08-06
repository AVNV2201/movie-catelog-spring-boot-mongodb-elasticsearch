package dev.brainstorm.moviecatelogmongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {

        Contact contact = new Contact("Abhinav Giri", "https://linkedin.com/in/abhinav-kumar-giri", "abhinav.cs2201@gmail.com");

        ApiInfo apiInfo = new ApiInfo(
                "Movie Catalog REST API",
                "Movie Catalog Spring Boot REST API for demo",
                "1.0",
                "Terms of service",
                contact,
                "Airtel License Version 2.0",
                "",
                new ArrayList<>());
        return apiInfo;
    }
}






