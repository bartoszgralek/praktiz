package com.gralek.praktiz;

import com.gralek.praktiz.service.DocsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PraktizApplication implements CommandLineRunner {

    @Autowired
    private DocsService docsService;

    public static void main(String[] args) {
        SpringApplication.run(PraktizApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        docsService.updateDatabase();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }
}
