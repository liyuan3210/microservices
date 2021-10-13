package com.liyuan3210.springcloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProviderMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderMain8002.class, args);
    }
}
