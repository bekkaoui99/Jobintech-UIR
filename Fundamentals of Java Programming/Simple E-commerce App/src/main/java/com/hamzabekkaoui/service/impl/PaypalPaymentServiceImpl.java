package com.hamzabekkaoui.service.impl;

import com.hamzabekkaoui.model.Order;
import com.hamzabekkaoui.model.Payment;
import com.hamzabekkaoui.model.PaymentDetails;
import com.hamzabekkaoui.service.PaymentService;

public class PaypalPaymentServiceImpl implements PaymentService {
    @Override
    public Payment processPayment(Order order, PaymentDetails paymentDetails) {
        return null;
    }
}
