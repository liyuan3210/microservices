package com.liyuan3210.springcloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerZkMain8080 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerZkMain8080.class, args);
    }
}
