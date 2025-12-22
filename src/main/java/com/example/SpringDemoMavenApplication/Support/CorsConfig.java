package com.example.SpringDemoMavenApplication.Support;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.example.SpringDemoMavenApplication.Constants.ConfigConstants.*;


@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Exclude Swagger UI endpoints from the global CORS settings
        registry.addMapping(SWAGGER_UI_ENDPOINT)
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.OPTIONS.name())
                .allowedOrigins(ALL_ORIGINS)
                .allowCredentials(Boolean.FALSE)
                .maxAge(MAX_AGE);

        // Apply global CORS settings to all other endpoints
        registry.addMapping(ALL_ENDPOINTS)
                .allowedMethods(HttpMethod.POST.name(), HttpMethod.GET.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name())
                .allowedOrigins(ALL_ORIGINS)
                .allowedHeaders(ALL_ORIGINS)
                .exposedHeaders(TOKEN)
                .allowCredentials(Boolean.FALSE)
                .maxAge(MAX_AGE);
    }
}
