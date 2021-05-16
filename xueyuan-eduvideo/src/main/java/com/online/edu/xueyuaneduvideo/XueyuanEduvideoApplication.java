package com.online.edu.xueyuaneduvideo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class XueyuanEduvideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(XueyuanEduvideoApplication.class, args);
    }

}
