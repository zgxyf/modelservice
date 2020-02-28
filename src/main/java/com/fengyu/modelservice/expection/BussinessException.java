package com.fengyu.modelservice.expection;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BussinessException extends RuntimeException{
    /**
     * errorCode
     */
    private String errorCode;

    public BussinessException(String errorCode) {
        this.errorCode = errorCode;
    }
}
