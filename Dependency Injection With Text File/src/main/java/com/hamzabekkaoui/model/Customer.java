package com.hamzabekkaoui.model;

import java.util.UUID;

public class Customer {

    private String id;
    private String userName;

    public Customer() {
        this.id = UUID.randomUUID().toString();
    }

    public Customer(String userName) {
        this();
        this.userName = userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
