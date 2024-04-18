package com.hamzabekkaoui.fakeapiservice.dto.request;

import java.math.BigDecimal;

public class PaymentDetailsRequest {
    private String paymentMethod;
    private BigDecimal amount;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
}
