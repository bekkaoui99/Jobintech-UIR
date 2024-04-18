package com.hamzabekkaoui.repository.jdbc;

import com.hamzabekkaoui.model.Order;
import com.hamzabekkaoui.model.OrderItems;
import com.hamzabekkaoui.repository.JDBCManager;
import com.hamzabekkaoui.repository.OrderRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryJDBC implements OrderRepository {

    private final JDBCManager jdbcManager;
    public OrderRepositoryJDBC(JDBCManager jdbcManager) {
        this.jdbcManager = jdbcManager;
    }



    @Override
    public Order create(Order order) throws SQLException {
        Order createdOrder = createOrder(order);
        createOrderItem(createdOrder);
        return createdOrder;
    }


    private Order createOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders VALUES (?, ? , ? , ? );";
        int executedOrder = jdbcManager.executeUpdate(
                sql,
                order.getId(),
                order.getOrderCode(),
                order.getTotal(),
                order.getCustomer().getId());

        if(executedOrder > 0){
            System.out.println("order is created successfully");
            return order;
        }
        else throw new IllegalArgumentException("something went wrong");
    }


    private void createOrderItem(Order order) throws SQLException {

        String sql = "INSERT INTO orderItem VALUES (?, ? , ? , ? , ?);";
        order.getOrderItems().forEach(orderItems -> {
            try {
                jdbcManager.executeUpdate(
                        sql,
                        orderItems.getId(),
                        order.getId(),
                        orderItems.getProduct().getId(),
                        orderItems.getQuantity(),
                        orderItems.getTotal());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });


    }

    @Override
    public Order createOrderWithTransaction(Order order) throws SQLException {
        Connection connection = jdbcManager.getConnection();

        connection.setAutoCommit(false);

        try {
            String orderSql = "INSERT INTO orders VALUES (?, ? , ?, ?)";
            PreparedStatement orderPreparedStatement = connection.prepareStatement(orderSql);
            orderPreparedStatement.setString(1 , order.getId());
            orderPreparedStatement.setString(2 , order.getOrderCode());
            orderPreparedStatement.setDouble(3 , order.getTotal());
            orderPreparedStatement.setString(4   , order.getCustomer().getId());
            int executed = orderPreparedStatement.executeUpdate();
            orderPreparedStatement.close();


            order.getOrderItems().forEach(orderItems -> {
                try {
                    String orderItemSql = "INSERT INTO orderItem VALUES (?, ? , ?, ? , ?)";
                    PreparedStatement orderItemPreparedStatement = connection.prepareStatement(orderItemSql);
                    orderItemPreparedStatement.setString(1 , orderItems.getId());
                    orderItemPreparedStatement.setString(2 , order.getId());
                    orderItemPreparedStatement.setString(3 , orderItems.getProduct().getId());
                    orderItemPreparedStatement.setInt(4   , orderItems.getQuantity());
                    orderItemPreparedStatement.setDouble(5   , orderItems.getTotal());
                    int executed1 = orderItemPreparedStatement.executeUpdate();
                    orderItemPreparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            });

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            System.err.println("Transaction rolled back due to an error: " + e.getMessage());
        }finally {
            connection.setAutoCommit(true);
        }

        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }
}
