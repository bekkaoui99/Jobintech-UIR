package com.hamzabekkaoui.fakeapiservice.controller;

import com.hamzabekkaoui.fakeapiservice.dto.response.PaymentDetailsResponse;
import com.hamzabekkaoui.fakeapiservice.dto.request.PaymentDetailsRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class PaymentGateWayController {


    @GetMapping("/payment")
    public PaymentDetailsResponse paymentGateWay(){
        return  PaymentDetailsResponse.builder()
                .message("success")
                .code(200)
                .build();
    }



}
