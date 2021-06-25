package com.example.paymentservice.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentApiBusinessException extends RuntimeException{


    private final String key;
    private final String[] args;


    public PaymentApiBusinessException(String key){
        super(key);
        this.key = key;
        args = new String[0];
    }


}
