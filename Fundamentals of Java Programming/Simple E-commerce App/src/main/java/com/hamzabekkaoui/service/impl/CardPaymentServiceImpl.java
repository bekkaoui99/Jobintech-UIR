package com.hamzabekkaoui.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamzabekkaoui.dto.PaymentGateWayResponse;
import com.hamzabekkaoui.model.Order;
import com.hamzabekkaoui.model.Payment;
import com.hamzabekkaoui.model.PaymentDetails;
import com.hamzabekkaoui.repository.PaymentRepository;
import com.hamzabekkaoui.service.PaymentService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class CardPaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public CardPaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    String apiUrl = "http://localhost:8080/api/v1/payment";
    String requestMethod = "GET";
    @Override
    public Payment processPayment(Order order, PaymentDetails paymentDetails) throws SQLException {
        PaymentGateWayResponse paymentGateWayResponse;
        try {
            String responseBody = RestClient.callApi(apiUrl , requestMethod , paymentDetails);
            ObjectMapper objectMapper = new ObjectMapper();

             paymentGateWayResponse = objectMapper.readValue(responseBody, PaymentGateWayResponse.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(paymentGateWayResponse.getCode() == 200){
            Payment payment = new Payment(order , order.getTotal() , new Date());
            paymentRepository.create(payment);
            return payment;
        }
        else throw new IllegalArgumentException("something went wrong");

    }
}
