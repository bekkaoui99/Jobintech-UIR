package com.hamzabekkaoui.service;

import com.hamzabekkaoui.model.Order;
import com.hamzabekkaoui.model.PaymentDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    Order create(Order order , PaymentDetails paymentDetails) throws SQLException;
    List<Order> getAllOrders();
}
