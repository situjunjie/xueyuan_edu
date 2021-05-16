package com.online.edu.staticservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StaticServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaticServiceApplication.class);
    }
}
