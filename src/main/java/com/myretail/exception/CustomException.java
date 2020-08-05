package com.myretail.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class CustomException extends JsonProcessingException {

    public CustomException(String id,String msg) {
        super(msg + id);
    }

}
