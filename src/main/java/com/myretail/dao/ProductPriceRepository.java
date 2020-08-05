package com.myretail.dao;

import com.myretail.model.ProductPriceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductPriceRepository extends MongoRepository<ProductPriceEntity, String> {
    Optional<ProductPriceEntity> findById(int id);
}
