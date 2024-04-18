package com.hamzabekkaoui.repository.jdbc;

import com.hamzabekkaoui.exception.ResourceAlreadyExistException;
import com.hamzabekkaoui.model.OrderItems;
import com.hamzabekkaoui.repository.JDBCManager;
import com.hamzabekkaoui.repository.OrderItemRepository;

import java.sql.SQLException;
import java.util.List;

public class OrderItemRepositoryJDBC implements OrderItemRepository {


    private final JDBCManager jdbcManager;
    public OrderItemRepositoryJDBC(JDBCManager jdbcManager) {
        this.jdbcManager = jdbcManager;
    }


    @Override
    public OrderItems create(OrderItems orderItems) throws SQLException {
        String sql = "INSERT INTO orderItem VALUES (?, ? , ? , ? , ?);";
        int executed = jdbcManager.executeUpdate(
                sql,
                orderItems.getId(),
                orderItems.getOrder(),
                orderItems.getProduct(),
                orderItems.getQuantity(),
                orderItems.getTotal());
        if(executed > 0){
            System.out.println("orderItem is created successfully");
            return orderItems;
        }
        else throw new IllegalArgumentException("something went wrong");
    }

    @Override
    public List<OrderItems> orderItems() {
        return null;
    }
}
