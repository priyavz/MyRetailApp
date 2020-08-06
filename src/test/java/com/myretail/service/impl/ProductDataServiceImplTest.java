package com.myretail.service.impl;

import com.myretail.common.exception.CustomException;
import com.myretail.common.exception.ProductNotFoundException;
import com.myretail.common.model.CurrentPrice;
import com.myretail.common.model.ProductData;
import com.myretail.service.ProductPriceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductDataServiceImplTest {

    @Mock
    ProductPriceService productPriceServiceMock;

    @Mock
    ProductDetailsServiceImpl productDetailsServiceMock;

    ProductDataServiceImpl productDataService;
    /**
     * Setup for Mockito before any test run.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productDataService = new ProductDataServiceImpl(productPriceServiceMock,productDetailsServiceMock);
    }

    @Test
    public void getProductById_dataPresent_successful() throws Exception{
        when(productPriceServiceMock.getPrice(Mockito.anyString())).thenReturn(new CurrentPrice(100.00,"USD"));
        when(productDetailsServiceMock.getProductName(Mockito.anyString())).thenReturn("Samsung BluRay Player");
        ProductData productData = productDataService.getProductDataById(Mockito.anyString());
        assertEquals(productData.getCurrent_price().getValue(),100.00,0);
        assertEquals(productData.getCurrent_price().getCurrency_code(),"USD");
        assertEquals(productData.getName(),"Samsung BluRay Player");
    }

    @Test
    public void getProductById_productNotFoundInDataStore_throwsProductNotFoundException() throws Exception{
        when(productPriceServiceMock.getPrice("1")).thenThrow( new ProductNotFoundException("1", "Product Id not found in datastore"));
        when(productDetailsServiceMock.getProductName("1")).thenReturn("Samsung BluRay Player");
        ProductNotFoundException ex = Assertions.assertThrows(ProductNotFoundException.class, () -> {
            productDataService.getProductDataById("1");
        });
        assertEquals(ex.getMessage(),"Product Id not found in datastore : 1");
    }

    @Test
    public void getProductById_productNotFoundInWebService_throwsProductNotFoundException() throws Exception{
        when(productPriceServiceMock.getPrice(Mockito.anyString())).thenReturn(new CurrentPrice(100.00,"USD"));
        when(productDetailsServiceMock.getProductName("1")).thenThrow( new ProductNotFoundException("1", "Unable to retrieve product details from look up service."));
        ProductNotFoundException ex = Assertions.assertThrows(ProductNotFoundException.class, () -> {
            productDataService.getProductDataById("1");
        });
        assertEquals(ex.getMessage(),"Unable to retrieve product details from look up service. : 1");
    }

    @Test
    public void getProductById_jsonProcessingException_throwsCustomException() throws Exception{
        when(productPriceServiceMock.getPrice(Mockito.anyString())).thenReturn(new CurrentPrice(100.00,"USD"));
        when(productDetailsServiceMock.getProductName("1")).thenThrow( new CustomException( "JsonProcessingException occurred while parsing response for ","{custom response}"));
        CustomException ex = Assertions.assertThrows(CustomException.class, () -> {
            productDataService.getProductDataById("1");
        });
        assertEquals(ex.getMessage(),"JsonProcessingException occurred while parsing response for {custom response}");
    }

    @Test
    public void getProductById_webClientException_throwsWebClientException() throws Exception{
        //TODO: Throw webclient exception.
    }
}
