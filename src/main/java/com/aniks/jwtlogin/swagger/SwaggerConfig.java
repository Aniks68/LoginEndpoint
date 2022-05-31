package com.aniks.jwtlogin.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .protocols(new HashSet<>(Arrays.asList("HTTP","HTTPs")))
                .apiInfo(metaData())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.aniks.serviceprovidersproject"))
                .paths(PathSelectors.any())
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/swagger-ui/")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiInfo metaData() {
        return new ApiInfo(
                "Service Providers Project",
                "Configuration for Restaurant Section",
                "1.1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Ikechukwu", " ", "https://ikechukwuanene.netlify.app/"),
                "Apache 2.0",
                "https://github.com/Aniks68/ServiceProvidersProject",
                Collections.emptyList()
        );
    }
    
}
