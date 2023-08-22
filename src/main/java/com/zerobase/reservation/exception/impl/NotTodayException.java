package com.zerobase.reservation.exception.impl;

import com.zerobase.reservation.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class NotTodayException extends AbstractException {


    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "예약된 방문일이 아닙니다.";
    }
}
