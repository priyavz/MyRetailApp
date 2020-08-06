package com.myretail.service.impl;

import com.myretail.common.exception.CustomException;
import com.myretail.common.exception.ProductNotFoundException;
import com.myretail.common.model.CurrentPrice;
import com.myretail.common.model.ProductData;
import com.myretail.service.ProductDataService;
import com.myretail.service.ProductDetailsService;
import com.myretail.service.ProductPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientException;

@Component
public class ProductDataServiceImpl implements ProductDataService {
    private static final Logger log = LoggerFactory.getLogger(ProductDataServiceImpl.class);

    private final ProductPriceService productPriceService;

    private final ProductDetailsService productDetailsService;

    @Autowired
    public ProductDataServiceImpl(ProductPriceService productPriceService, ProductDetailsServiceImpl productDetailsService) {
        this.productPriceService = productPriceService;
        this.productDetailsService = productDetailsService;
    }

    @Override
    public ProductData getProductDataById(String productId) throws CustomException, WebClientException, ProductNotFoundException {

        CurrentPrice currentPrice  = productPriceService.getPrice(productId);
        log.debug("{} :: Setting current price for the product with id {}.. ", Thread.currentThread().getId(), productId);

        String productName = productDetailsService.getProductName(productId);
        log.debug("{} :: Setting product name for productId {}  ", Thread.currentThread().getId(), productId);

        return new ProductData(productId,productName,currentPrice);
    }



}
