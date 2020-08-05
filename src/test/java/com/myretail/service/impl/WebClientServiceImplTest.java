package com.myretail.service.impl;

import com.myretail.service.WebClientService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

public class WebClientServiceImplTest {

    private Environment environment;

    private WebClientService webClientService;
    /**
     * Setup for Mockito before any test run.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        webClientService = new WebClientServiceImpl(environment);
    }
    @Test
    public void getProductDetailsByProductId_productExists_success() {
        webClientService.getProductDetailsById("1");
    }
}
