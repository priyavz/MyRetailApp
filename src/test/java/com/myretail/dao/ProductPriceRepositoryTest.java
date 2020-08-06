package com.myretail.dao;

import com.myretail.common.dao.ProductPriceRepository;
import com.myretail.common.model.ProductPriceEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductPriceRepositoryTest {

    @Autowired
    ProductPriceRepository productPriceRepository;

    @Before
    public void before() {
        productPriceRepository.save(new ProductPriceEntity("1", 100.00, "USD"));
    }

    @Test
    public void findById_presentInDb_success() {
        Optional<ProductPriceEntity> productPriceEntityOptional = productPriceRepository.findById("1");

        assertTrue(productPriceEntityOptional.isPresent());
        assertEquals(productPriceEntityOptional.get().getPrice().doubleValue(),100.00,0.0);
    }

    @Test
    public void findById_notPresentInDb_failure() {
        Optional<ProductPriceEntity> productPriceEntityOptional = productPriceRepository.findById("2");
        assertFalse(productPriceEntityOptional.isPresent());
    }

    @After
    public void after() {
        productPriceRepository.delete(new ProductPriceEntity("1", 100.00, "USD"));
    }

}
