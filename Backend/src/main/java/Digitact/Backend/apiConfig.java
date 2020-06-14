package Digitact.Backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;


@Configuration
public class apiConfig {

	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
        	@Override
        	public void addCorsMappings(CorsRegistry registry) {
        		registry.addMapping("/**")
        			.allowedOrigins("http://localhost:4200")
        			.allowedMethods("GET", "POST")
        			.allowCredentials(false).maxAge(3600);
        	}
        	
        	@Override
            public void configureContentNegotiation( ContentNegotiationConfigurer configurer )
            {
                configurer.defaultContentType( MediaType.APPLICATION_JSON );
            }
        };
    }
	
}
