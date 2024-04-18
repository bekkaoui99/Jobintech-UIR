package com.hamzabekkaoui.model;

import java.util.UUID;

public class Product {


    private String id;
    private String name;
    private String productDescription;
    private Double price;
    private Integer quantity;


    public Product() {
        this.id = UUID.randomUUID().toString();
    }

    public Product(String id,String name, String productDescription, Double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.productDescription = productDescription;
        this.price = price;
        this.quantity = quantity;
    }
    public Product(String name, String productDescription, Double price, Integer quantity) {
        this();
        this.name = name;
        this.productDescription = productDescription;
        this.price = price;
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductDescription(String description) {
        this.productDescription = description;
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

    public String getProductDescription() {
        return productDescription;
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
                ", description='" + productDescription + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

}
