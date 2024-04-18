package com.hamzabekkaoui.repository;

import com.hamzabekkaoui.model.OrderItems;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemRepository {

    OrderItems create(OrderItems orderItems) throws SQLException;
    List<OrderItems> orderItems();

}
