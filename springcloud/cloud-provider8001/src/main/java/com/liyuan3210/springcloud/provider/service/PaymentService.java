package com.liyuan3210.springcloud.provider.service;

import com.liyuan3210.springcloud.common.entity.Payment;

public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
