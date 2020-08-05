package com.myretail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String id,String msg) {
        super(String.format("%s : %s",msg , id));
    }
}
