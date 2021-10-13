package com.liyuan3210.springcloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConsumerMain8080 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerMain8080.class, args);
    }
}
