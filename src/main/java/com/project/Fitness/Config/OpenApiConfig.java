package com.project.Fitness.Config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customAPI()
    {
        return new OpenAPI()
                .info(new Info()
                        .title("Fitness Tracking API")
                        .version("v-1.0")
                        .description("Production Grade API's")
                        .contact(new Contact()
                                .name("VARUN")
                                        .email("varun.math01@gmail.com")
                                 )
                );
    }

}
