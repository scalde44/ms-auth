package co.com.pragma.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI crediyaAuthOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CrediYa - Auth API")
                        .version("v1")
                        .description("Endpoints para gestión de usuarios"));
    }
}
