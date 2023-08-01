package com.example.dividends.exception.impl;

import com.example.dividends.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class NoCompanyException extends AbstractException {

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "회사 정보가 존재하지 않습니다.";
    }
}
