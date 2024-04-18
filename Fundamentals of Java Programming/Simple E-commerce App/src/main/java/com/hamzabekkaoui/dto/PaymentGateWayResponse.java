package com.hamzabekkaoui.dto;

public class PaymentGateWayResponse {

    private  String message;
    private  int code;

    public PaymentGateWayResponse() {

    }
    public PaymentGateWayResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
