package com.hamzabekkaoui.service.impl;

import com.hamzabekkaoui.model.Order;
import com.hamzabekkaoui.service.OrderService;
import com.hamzabekkaoui.service.PaymentService;

public class OrderServiceImpl implements OrderService {

    private final PaymentService paymentService;
    public OrderServiceImpl(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @Override
    public Order order(Order order) {
        String payment = paymentService.payment(order);
        System.out.println(payment);
        return order;
    }
}
