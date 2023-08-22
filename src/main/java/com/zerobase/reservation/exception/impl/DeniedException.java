package com.zerobase.reservation.exception.impl;

import com.zerobase.reservation.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class DeniedException extends AbstractException {


    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "거절된 예약입니다.";
    }
}
