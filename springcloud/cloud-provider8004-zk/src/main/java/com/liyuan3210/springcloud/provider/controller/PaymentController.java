package com.liyuan3210.springcloud.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    /**
     * http://localhost:8004/payment/zk
     *
     * @return
     */
    @RequestMapping(value = "payment/zk")
    public String paymentZk() {
        return "SpringCloud with zookeeper:" + serverPort + "\t" + UUID.randomUUID().toString();
    }
    @RequestMapping(value = "payment/zk/gateway/{id}")
    public String paymentZk2(@PathVariable("id") Long id) {
        return "SpringCloud with zookeeper:" + serverPort + "\t" + UUID.randomUUID().toString()+" id:"+id;
    }
}
