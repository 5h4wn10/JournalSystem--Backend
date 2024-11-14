package com.journalsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Använd din frontend-URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Säkerställ att OPTIONS är tillåten
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
