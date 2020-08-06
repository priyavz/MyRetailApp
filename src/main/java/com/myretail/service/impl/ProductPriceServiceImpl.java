package com.myretail.service.impl;

import com.myretail.common.dao.ProductPriceRepository;
import com.myretail.common.exception.ProductNotFoundException;
import com.myretail.common.model.CurrentPrice;
import com.myretail.common.model.ProductPriceEntity;
import com.myretail.service.ProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductPriceServiceImpl implements ProductPriceService {

    private final ProductPriceRepository productPriceRepository;

    @Autowired
    public ProductPriceServiceImpl(ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    @Override
    public CurrentPrice getPrice(String productId) throws ProductNotFoundException {
        Optional<ProductPriceEntity> productPriceEntity = productPriceRepository.findById(productId);

        if (!productPriceEntity.isPresent())
            throw new ProductNotFoundException(productId, "Product Id not found in datastore");

        return new CurrentPrice(productPriceEntity.get().getPrice(), productPriceEntity.get().getCurrencyCode());
    }

}
