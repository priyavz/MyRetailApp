package com.myretail.service;

import com.myretail.common.exception.CustomException;
import com.myretail.common.exception.ProductNotFoundException;
import com.myretail.common.model.ProductData;
import org.springframework.web.reactive.function.client.WebClientException;

public interface ProductDataService {

    ProductData getProductDataById(String productId) throws ProductNotFoundException, WebClientException, CustomException;

}
