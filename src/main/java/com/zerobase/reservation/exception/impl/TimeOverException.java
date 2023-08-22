package com.zerobase.reservation.exception.impl;

import com.zerobase.reservation.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class TimeOverException extends AbstractException {


    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "방문 확인이 가능한 시간이 지났습니다.";
    }
}
