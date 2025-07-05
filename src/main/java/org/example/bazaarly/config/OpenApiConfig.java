package org.example.bazaarly.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.example.bazaarly.entity.Category;
import org.example.bazaarly.repo.CategoryRepository;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Bazaarly", version = "v1", description = "Demo API"),
        servers = @Server(url = "http://localhost:8080")
)
public class OpenApiConfig {

    @Bean
    public OpenAPI customizeOpenAPI() {
        final String SCHEME_NAME = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME))
                .components(new Components().addSecuritySchemes(SCHEME_NAME,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                ));

    }

    @Bean
    public OpenApiCustomizer dynamicFilter(CategoryRepository categoryRepository) {
        return openApi -> {
            List<Category> categories = categoryRepository.findAll();
            List<String> categoryNames = categories.stream().map(Category::getName).toList();
            openApi.getPaths().forEach(( path,  pathItem) -> {
                pathItem.readOperations().forEach( operation -> {
                    List<Parameter> parameters = operation.getParameters();
                    if (parameters != null) {
                        parameters.forEach( parameter -> {
                            if ("categoryName".equals(parameter.getName())) {
                                Schema<String> schema = parameter.getSchema();
                                if (schema != null) {
                                    schema.setEnum(categoryNames);
                                }
                            }
                        });
                    }
                });
            });
        };
    }

}
