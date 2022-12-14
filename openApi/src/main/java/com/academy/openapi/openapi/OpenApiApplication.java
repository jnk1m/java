package com.academy.openapi.openapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class OpenApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenApiApplication.class, args);

    }
        @Bean
        public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
            return restTemplateBuilder
                    .setReadTimeout(Duration.of(5L, ChronoUnit.SECONDS))
                    .setConnectTimeout(Duration.of(3L, ChronoUnit.SECONDS))
                    .build();
        }

}
