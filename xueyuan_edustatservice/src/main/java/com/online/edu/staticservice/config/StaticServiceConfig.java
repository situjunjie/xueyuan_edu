package com.online.edu.staticservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableEurekaClient
@EnableSwagger2
@MapperScan("com.online.edu.staticservice.mapper")
public class StaticServiceConfig {
}
