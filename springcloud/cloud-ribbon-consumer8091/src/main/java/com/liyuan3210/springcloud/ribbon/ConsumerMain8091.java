package com.liyuan3210.springcloud.ribbon;

import com.liyuan3210.springcloud.ribbonrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PROVIDER", configuration = MySelfRule.class)
public class ConsumerMain8091 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerMain8091.class, args);
    }
}
