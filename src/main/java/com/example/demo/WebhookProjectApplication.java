package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.runner.*;

@SpringBootApplication
public class WebhookProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebhookProjectApplication.class, args);
    }

    @Bean
    CommandLineRunner run(StartupRunner runner) {
        return args -> runner.execute();
    }
}