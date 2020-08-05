package com.myretail.service.impl;

import com.myretail.dao.ProductPriceRepository;
import com.myretail.exception.ProductNotFoundException;
import com.myretail.model.CurrentPrice;
import com.myretail.model.ProductPriceEntity;
import com.myretail.service.ProductPriceService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductPriceServiceImplTest {

    ProductPriceService productPriceService;
    @Mock
    ProductPriceRepository productPriceRepositoryMock;

    /**
     * Setup for Mockito before any test run.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productPriceService = new ProductPriceServiceImpl(productPriceRepositoryMock);
    }

    @Test
    public void getPrice_productExists_success() {
        when(productPriceRepositoryMock.findById(Mockito.anyString())).thenReturn(Optional.of(new ProductPriceEntity("1",100.00,"USD")));
        CurrentPrice currentPrice = productPriceService.getPrice("1");
        Assert.assertEquals(currentPrice.getValue(),100.00,0);
        Assert.assertEquals(currentPrice.getCurrency_code(),"USD","USD");
    }

    @Test
    public void getPrice_productDoesNotExist_throwsProductNotFoundException() {
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            productPriceService.getPrice("1");
        });

        String expectedMessage = "Product Id not found in datastore : 1";
        String actualMessage = exception.getMessage();

        System.out.print("actualMessage2342432 "+actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
