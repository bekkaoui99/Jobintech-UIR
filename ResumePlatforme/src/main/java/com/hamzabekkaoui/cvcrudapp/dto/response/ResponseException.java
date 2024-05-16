package com.hamzabekkaoui.cvcrudapp.dto.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ResponseException(
        String message,
        HttpStatus httpStatus,
        int code
) {
}
