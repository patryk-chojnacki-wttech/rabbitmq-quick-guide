package com.wtt.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.util.Scanner;

@SpringBootApplication
@PropertySource(value = {"file:application.properties"})
public class PublisherApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublisherApplication.class, args);
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

}
