package com.hamzabekkaoui.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private String id;
    private String orderCode;
    private List<Product> products = new ArrayList<>();
    private Customer customer;

    public Order() {
        this.id = UUID.randomUUID().toString();
        this.orderCode = UUID.randomUUID().toString();
    }

    public Order(List<Product> products, Customer customer) {
        this();
        this.products = products;
        this.customer = customer;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", products=" + products +
                ", customer=" + customer +
                '}';
    }
}
