package Digitact.Backend;

import static Digitact.Backend.ConfigProperties.SecurityConstants.DEVICE_HEADER_STRING;
import static Digitact.Backend.ConfigProperties.SecurityConstants.USER_HEADER_STRING;
import static Digitact.Backend.ConfigProperties.absoluteClientURL_ionic;
import static Digitact.Backend.ConfigProperties.absoluteClientURL_ng;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** @descriptions This class is configure client with server */
@Configuration
public class apiConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /** @descriptions This method configures cross origin requests */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(absoluteClientURL_ng, absoluteClientURL_ionic)
                        .exposedHeaders(USER_HEADER_STRING, DEVICE_HEADER_STRING)
                        .allowedMethods("GET", "POST")
                        .allowCredentials(false)
                        .maxAge(3600);
            }
            /** @descriptions This method restricts content type to application/json */
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                configurer.defaultContentType(MediaType.APPLICATION_JSON);
            }
        };
    }
}
