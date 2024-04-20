package com.hamzabekkaoui.model;

import java.util.UUID;

public class Product {

    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;


    public Product() {
        this.id = UUID.randomUUID().toString();
    }
    public Product(String name, String description, Double price, Integer quantity) {
        this();
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
