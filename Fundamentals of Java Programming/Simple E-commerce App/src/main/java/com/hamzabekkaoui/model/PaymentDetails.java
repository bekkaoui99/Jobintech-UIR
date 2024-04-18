package com.hamzabekkaoui.model;

import java.math.BigDecimal;

public class PaymentDetails {

    private String paymentMethod;
    private Double amount;
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    public PaymentDetails() {
    }

    public PaymentDetails(String paymentMethod, Double amount, String cardNumber, String expiryDate, String cvv) {
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
