package com.liyuan3210.springcloud.ribbon.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean
//    @LoadBalanced//使用自定义负载均衡规则,注释掉@LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
