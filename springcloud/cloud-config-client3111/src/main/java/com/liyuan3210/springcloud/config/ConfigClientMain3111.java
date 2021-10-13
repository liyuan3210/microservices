package com.liyuan3210.springcloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClientMain3111 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3111.class, args);
    }
}
