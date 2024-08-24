package com.carsale.cars.infra.swagger;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.Set;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Car Used Sale")
                        .version("v1")
                        .description("Documentação da API Car Used Sale\n\nURL Base: https://")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    @Bean
    public GroupedOpenApi api(RequestMappingHandlerMapping handlerMapping) {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/users/**", "/used-cars/**")
                .packagesToScan("com.carsale.cars.controller")
                .addOpenApiCustomizer(filterControllers(handlerMapping))
                .build();
    }

    @Bean
    public OpenApiCustomizer filterControllers(RequestMappingHandlerMapping handlerMapping) {
        return openApi -> {
            Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
            handlerMethods.forEach((requestMappingInfo, handlerMethod) -> {
                if (!handlerMethod.getBeanType().isAnnotationPresent(Tag.class)) {
                    Set<String> patterns = requestMappingInfo.getPatternsCondition() != null ? requestMappingInfo.getPatternsCondition().getPatterns() : null;
                    if (patterns != null) {
                        patterns.forEach(openApi.getPaths()::remove);
                    }
                }
            });
        };
    }

   /* @Bean
    public GroupedOpenApi api(RequestMappingHandlerMapping handlerMapping) {
        return GroupedOpenApi.builder()
                .group("api")
                .packagesToScan("com.estudo.api_med_voll.controller")
                .addOpenApiCustomizer(filterControllers(handlerMapping))
                .build();
    }

    @Bean
    public OpenApiCustomizer customizer() {
        return openApi -> {
            openApi.getComponents().getSchemas().remove("UnwantedModelName");  // Remover modelos indesejados
        };
    }

    @Bean
    public OpenApiCustomizer filterControllers(RequestMappingHandlerMapping handlerMapping) {
        return openApi -> {
            Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
            handlerMethods.forEach((requestMappingInfo, handlerMethod) -> {
                if (!handlerMethod.getBeanType().isAnnotationPresent(Tag.class)) {
                    Set<String> patterns = requestMappingInfo.getPatternsCondition() != null ? requestMappingInfo.getPatternsCondition().getPatterns() : null;
                    if (patterns != null) {
                        patterns.forEach(openApi.getPaths()::remove);
                    }
                }
            });
        };
    }

    private void filterPathItemOperations(PathItem pathItem) {
        pathItem.readOperations().removeIf(operation -> !hasOperationAnnotation(operation));
    }

    private boolean hasOperationAnnotation(Operation operation) {
        return operation.getExtensions() != null && operation.getExtensions().containsKey("x-operation");
    }*/
}


