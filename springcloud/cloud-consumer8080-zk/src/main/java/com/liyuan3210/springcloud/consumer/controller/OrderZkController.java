package com.liyuan3210.springcloud.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


@RestController
public class OrderZkController {

    public static final String INVOKE_URL = "http://cloud-provider-zk";
    @Resource
    private RestTemplate restTemplate;


    /**
     * http://localhost:8080/consumer/payment/zk
     *
     * @return
     */
    @GetMapping("/consumer/payment/zk")
    public String paymentInfo() {
        return restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
    }
}
