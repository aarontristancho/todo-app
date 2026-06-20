package com.aarontristancho.todoapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// OpenAPI/Swagger configuration.
// Defines the metadata displayed in the generated API documentation,
// such as the title, version, and description.

@Configuration
public class OpenApiConfig {

    // Creates the OpenAPI bean used by Swagger UI to generate the API documentation metadata.
    @Bean
    public OpenAPI openAPI() {
        OpenAPI openAPI = new OpenAPI();
        Info info = new Info()
                .title("To Do App")
                .version("0.0.1-SNAPSHOT")
                .description("This is a To Do Application. This is a system to manage all your tasks.");
        openAPI.info(info);
        return openAPI;
    }


}
