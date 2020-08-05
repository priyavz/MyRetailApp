package com.myretail.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.myretail.exception.ProductNotFoundException;
import com.myretail.model.ProductData;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.NoSuchElementException;

public interface ProductDataService {

    ProductData getProductDataById(String productId) throws ProductNotFoundException, WebClientException,JsonProcessingException;

}
