package com.hamzabekkaoui.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private String id;
    private String orderCode;
    private List<OrderItems> orderItems = new ArrayList<>();
    private Double total;
    private Customer customer;

    public Order() {
        this.id = UUID.randomUUID().toString();
        this.orderCode = UUID.randomUUID().toString();
    }

    public Order(List<OrderItems> orderItems, Customer customer , Double total) {
        this();
        this.orderItems = orderItems;
        this.customer = customer;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    public void setProducts(List<OrderItems> orderItems) {
        this.orderItems = orderItems ;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public String getOrderCode() {
        return orderCode;
    }

    public List<OrderItems> getProducts() {
        return orderItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", orderItems=" + orderItems +
                ", customer=" + customer +
                '}';
    }

}
