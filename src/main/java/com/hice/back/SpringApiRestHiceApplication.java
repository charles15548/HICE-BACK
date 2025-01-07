package com.hice.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hice.back.security.SecurityConfig;

@SpringBootApplication
public class SpringApiRestHiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringApiRestHiceApplication.class, args);
	}
	
	  @Configuration
	    public class WebConfig implements WebMvcConfigurer {
	        @Override
	        public void addResourceHandlers(ResourceHandlerRegistry registry) {
	            registry.addResourceHandler("/Archivo/Voz/**")
	                .addResourceLocations("file:./Archivo/Voz/");
	        }
	    }

}
