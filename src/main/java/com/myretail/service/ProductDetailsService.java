package com.myretail.service;

import com.myretail.common.exception.CustomException;
import com.myretail.common.exception.ProductNotFoundException;
import org.springframework.web.reactive.function.client.WebClientException;

public interface ProductDetailsService {
    String getProductName(String productId)  throws CustomException, WebClientException, ProductNotFoundException;
}
