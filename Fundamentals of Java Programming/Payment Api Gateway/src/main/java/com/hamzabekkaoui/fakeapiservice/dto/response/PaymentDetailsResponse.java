package com.hamzabekkaoui.fakeapiservice.dto.response;

import lombok.Builder;

@Builder
public record PaymentDetailsResponse(
        String message,
        int code
) {

}
