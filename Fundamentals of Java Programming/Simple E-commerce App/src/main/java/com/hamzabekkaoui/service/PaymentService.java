package com.hamzabekkaoui.service;

import com.hamzabekkaoui.model.Order;
import com.hamzabekkaoui.model.Payment;
import com.hamzabekkaoui.model.PaymentDetails;

import java.io.IOException;
import java.sql.SQLException;

public interface PaymentService {
    Payment processPayment(Order order, PaymentDetails paymentDetails) throws SQLException;
}
