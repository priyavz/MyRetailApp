package com.myretail.service;

import com.myretail.common.exception.ProductNotFoundException;
import com.myretail.common.model.CurrentPrice;

public interface ProductPriceService {

    CurrentPrice getPrice(String productId) throws ProductNotFoundException;

}
