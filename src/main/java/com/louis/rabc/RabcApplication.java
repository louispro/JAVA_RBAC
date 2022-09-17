package com.louis.rabc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabcApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabcApplication.class, args);
    }

}
