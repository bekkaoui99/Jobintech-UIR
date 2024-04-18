package com.hamzabekkaoui.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Payment {

    private String id;

    private Order order;

    private Double amount;

    private Date paymentDate;

    public Payment() {
        this.id = UUID.randomUUID().toString();
    }

    public Payment(Order order, Double amount, Date paymentDate) {
        this();
        this.order = order;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public String getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Double getAmount() {
        return amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
