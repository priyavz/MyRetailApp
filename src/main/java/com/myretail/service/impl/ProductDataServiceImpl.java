package com.myretail.service.impl;

import com.myretail.common.exception.CustomException;
import com.myretail.common.exception.ProductNotFoundException;
import com.myretail.common.model.CurrentPrice;
import com.myretail.common.model.ProductData;
import com.myretail.service.ProductDataService;
import com.myretail.service.ProductDetailsService;
import com.myretail.service.ProductPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientException;

@Component
@Slf4j
public class ProductDataServiceImpl implements ProductDataService {

    private final ProductPriceService productPriceService;

    private final ProductDetailsService productDetailsService;

    @Autowired
    public ProductDataServiceImpl(ProductPriceService productPriceService, ProductDetailsServiceImpl productDetailsService) {
        this.productPriceService = productPriceService;
        this.productDetailsService = productDetailsService;
    }

    @Override
    public ProductData getProductDataById(String productId) throws CustomException, WebClientException, ProductNotFoundException {
        CurrentPrice currentPrice = productPriceService.getPrice(productId);
        log.debug("productId={} msg=Setting current price", productId);

        String productName = productDetailsService.getProductName(productId);
        log.debug("productId={} msg=Setting product name", productId);

        return new ProductData(productId, productName, currentPrice);
    }

}
