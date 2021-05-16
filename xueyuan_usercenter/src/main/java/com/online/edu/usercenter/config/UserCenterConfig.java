package com.online.edu.usercenter.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableEurekaClient
@MapperScan("com.online.edu.usercenter.mapper")
public class UserCenterConfig {
}
