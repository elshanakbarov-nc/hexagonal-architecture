package com.example.commons.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiBusinessException extends RuntimeException{

    private final String key;
    private final String[] args;

    public ApiBusinessException(String key){
        super(key);
        this.key = key;
        args = new String[0];
    }

}
