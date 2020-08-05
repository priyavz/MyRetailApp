package com.myretail.exception;

import org.springframework.web.reactive.function.client.WebClientException;

public class CustomWebClientException extends WebClientException {
    public CustomWebClientException(String msg) {
        super(msg);
    }
}
