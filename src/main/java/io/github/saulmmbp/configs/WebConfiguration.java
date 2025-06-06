package io.github.saulmmbp.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

import lombok.Setter;

@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.web.cors")
public class WebConfiguration {

    private String[] allowedOrigins;
    
    @Bean
    WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins(allowedOrigins);
            }
            
        };
    }
    
}
