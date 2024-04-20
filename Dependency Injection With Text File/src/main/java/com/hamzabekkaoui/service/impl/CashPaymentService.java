package com.hamzabekkaoui.service.impl;

import com.hamzabekkaoui.model.Order;
import com.hamzabekkaoui.service.PaymentService;

public class CashPaymentService implements PaymentService {
    @Override
    public String payment(Order order) {
        System.out.println(order);
        return "CashPaymentService";
    }
}
