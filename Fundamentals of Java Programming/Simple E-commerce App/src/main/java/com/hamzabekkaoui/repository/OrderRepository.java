package com.hamzabekkaoui.repository;

import com.hamzabekkaoui.model.Order;
import com.hamzabekkaoui.model.OrderItems;

import java.sql.SQLException;
import java.util.List;

public interface OrderRepository {

    Order create(Order order) throws SQLException;

    Order createOrderWithTransaction(Order order) throws SQLException;
    List<Order> getAllOrders();
}
