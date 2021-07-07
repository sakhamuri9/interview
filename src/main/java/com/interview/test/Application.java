package com.interview.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ComponentScan(basePackages = "com.*")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(com.interview.test.Application.class, args);
    }


    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }


}
