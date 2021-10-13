package com.liyuan3210.springcloud.consumer.controller;

import com.liyuan3210.springcloud.common.entity.CommonResult;
import com.liyuan3210.springcloud.common.entity.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.net.URI;
import java.util.List;


@RestController
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PROVIDER";

    @Resource
    private RestTemplate restTemplate;

    /**
     * http://localhost:8080/consumer/payment/create?serial=liyuan3210
     *
     * @param payment
     * @return
     */
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    /**
     * http://localhost:8080/consumer/payment/get/8
     *
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }


}
