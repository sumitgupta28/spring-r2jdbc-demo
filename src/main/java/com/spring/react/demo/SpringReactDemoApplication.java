package com.spring.react.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories(basePackages = {"com.spring.react.demo"})
public class SpringReactDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringReactDemoApplication.class, args);
    }

}
