package com.hamzabekkaoui.model;

import java.util.UUID;

public class OrderItems {
    private String id;
    private Order order;
    private Product product;
    private Integer quantity;
    private Double total;

    public OrderItems() {
       this.id = UUID.randomUUID().toString();
    }
    public OrderItems(Order order, Product product, Integer quantity, Double total) {
        this();
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.total = total;
    }
    public OrderItems(String id ,Order order, Product product, Integer quantity, Double total) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "id='" + id + '\'' +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }
}
