package com.myretail.controller;

import com.myretail.common.exception.CustomException;
import com.myretail.common.exception.ProductNotFoundException;
import com.myretail.common.model.CurrentPrice;
import com.myretail.common.model.ProductData;
import com.myretail.service.impl.ProductDataServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MyRetailControllerTest {

    @InjectMocks
    MyRetailController myRetailController;

    @Mock
    ProductDataServiceImpl productDataServiceMock;

    /**
     * Setup for Mockito before any test run.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        myRetailController = new MyRetailController(productDataServiceMock);
    }

    @Test
    public void testGetProduct_productValid_successful() throws Exception
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(productDataServiceMock.getProductDataById("1")).thenReturn(new ProductData("1","Samsung Player",new CurrentPrice(100.00,"USD")));
        ResponseEntity<Object> responseEntity = myRetailController.getProductDetails("1");
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void testGetProduct_productNotFoundInDataStore_throwsProductNotFoundException() throws Exception
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(productDataServiceMock.getProductDataById("1")).thenThrow( new ProductNotFoundException("1", "Product Id not found in datastore"));
        ProductNotFoundException ex = Assertions.assertThrows(ProductNotFoundException.class, () -> {
          myRetailController.getProductDetails("1");
        });
        assertEquals(ex.getMessage(), "Product Id not found in datastore : 1");
    }

    @Test
    public void testGetProduct_productNotFoundInWebService_throwsProductNotFoundException() throws Exception
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(productDataServiceMock.getProductDataById("1")).thenThrow(  new ProductNotFoundException("1", "Unable to retrieve product details from look up service."));
        ProductNotFoundException ex = Assertions.assertThrows(ProductNotFoundException.class, () -> {
            myRetailController.getProductDetails("1");
        });
        assertEquals(ex.getMessage(), "Unable to retrieve product details from look up service. : 1");
    }

    @Test
    public void testGetProduct_jsonProcessingException_throwsCustomException() throws Exception{
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(productDataServiceMock.getProductDataById("1")).thenThrow(  new CustomException("JsonProcessingException occurred while parsing response for ","{custom response}"));
        CustomException ex = Assertions.assertThrows(CustomException.class, () -> {
            myRetailController.getProductDetails("1");
        });
        assertEquals(ex.getMessage(),"JsonProcessingException occurred while parsing response for {custom response}");
    }
}
