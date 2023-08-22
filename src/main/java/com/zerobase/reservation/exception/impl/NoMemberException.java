package com.zerobase.reservation.exception.impl;

import com.zerobase.reservation.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class NoMemberException extends AbstractException {


    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "회원가입 진행 후 이용해주세요.";
    }
}
