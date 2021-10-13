package com.liyuan3210.cloudalibaba.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProviderMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderMain9002.class, args);
    }
}
