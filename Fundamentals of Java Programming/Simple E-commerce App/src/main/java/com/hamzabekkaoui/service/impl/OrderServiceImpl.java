package com.hamzabekkaoui.service.impl;

import com.hamzabekkaoui.model.Order;
import com.hamzabekkaoui.model.OrderItems;
import com.hamzabekkaoui.model.Payment;
import com.hamzabekkaoui.model.PaymentDetails;
import com.hamzabekkaoui.repository.OrderItemRepository;
import com.hamzabekkaoui.repository.OrderRepository;
import com.hamzabekkaoui.repository.PaymentRepository;
import com.hamzabekkaoui.service.OrderService;
import com.hamzabekkaoui.service.PaymentService;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final PaymentService paymentService;

    public OrderServiceImpl(OrderRepository orderRepository,PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
    }


    @Override
    public Order create(Order order , PaymentDetails paymentDetails) throws SQLException {
        Order createdOrder = orderRepository.createOrderWithTransaction(order);
        paymentService.processPayment(createdOrder, paymentDetails);
       return createdOrder;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }
}
