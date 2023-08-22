package com.zerobase.reservation.exception.impl;

import com.zerobase.reservation.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class AlreadyVisitedException extends AbstractException {


    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "이미 방문처리가 완료된 예약입니다.";
    }
}
