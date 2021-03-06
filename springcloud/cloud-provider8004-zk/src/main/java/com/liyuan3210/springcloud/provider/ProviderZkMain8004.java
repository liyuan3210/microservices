package com.liyuan3210.springcloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 支付模块启动类-Zookeeper
 * EnableDiscoveryClient 该注解用于向使用consul或者zookeeper作为注册中心时注册服务
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderZkMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderZkMain8004.class, args);
    }
}
