package com.myretail.service.impl;

import com.myretail.model.CurrentPrice;
import com.myretail.model.ProductData;
import com.myretail.model.ProductPriceEntity;
import com.myretail.service.ProductDataService;
import com.myretail.service.ProductDetailsService;
import com.myretail.service.ProductPriceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductDataServiceImplTest {

    @Mock
    ProductPriceService productPriceRepositoryMock;

    @Mock
    ProductDetailsServiceImpl productDetailsService;

    @Mock
    ProductDataServiceImpl productDataService;
    /**
     * Setup for Mockito before any test run.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productDataService = new ProductDataServiceImpl(productPriceRepositoryMock,productDetailsService);
    }

    @Test
    public void getProductByIdTest() throws Exception{

//        //Objects created for the actual Mock
//        CurrentPrice currentPriceMock = new CurrentPrice(13.49,"USD");
////        ProductData productData = new ProductData("13860428",) ;
//        Optional<ProductPriceEntity> productPriceEntityOptionalMock = Optional.of(new ProductPriceEntity("1",100.00,"USD"));
//        Mockito.when(productPriceRepositoryMock.findById(Mockito.anyString())).thenReturn(productPriceEntityOptionalMock);
//        Mono<String> monoResponseMock = Mono.just("1");
//        Mockito.when(myRetailServiceMock.getProductDetailsById(Mockito.anyString())).thenReturn(Mono.just(ClientResponse.create(HttpStatus.ACCEPTED).build()));
//
//        System.out.println("productPriceRepositoryMock {}"+productPriceRepositoryMock);
//        System.out.println("myRetailServiceMock {}"+myRetailServiceMock);
//
//        ProductData productData = productDataService.getProductDataById("1");
//        assertEquals("1",productData.getId());
//        assertEquals(100.00,productData.getCurrent_price());
    }
}
