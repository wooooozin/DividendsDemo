package com.example.dividends.exception.impl;

import com.example.dividends.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class UnmatchedPasswordException extends AbstractException {

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "비밀번호가 일치하지 않습니다.";
    }
}
