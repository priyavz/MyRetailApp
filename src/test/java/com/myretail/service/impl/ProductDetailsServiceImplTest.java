package com.myretail.service.impl;

import com.myretail.common.exception.CustomException;
import com.myretail.common.exception.ProductNotFoundException;
import com.myretail.service.ProductDetailsService;
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
import org.springframework.http.HttpStatus;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductDetailsServiceImplTest {

    MockEnvironment mockEnvironment;
    @Mock
    private WebClientServiceImpl webClientMock;

    ProductDetailsService productDetailsService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
         mockEnvironment = new MockEnvironment();

        if (System.getProperty("spring.profiles.active")!=null) {
            mockEnvironment.setProperty("spring_profiles", System.getProperty("spring.profiles.active"));
        } else {
            mockEnvironment.setProperty("spring_profiles", "default");
        }
        mockEnvironment.setProperty("target.restUrl1","https://redsky.target.com");
        mockEnvironment.setProperty("target.restUrl2","/v3/pdp/tcin/");
        mockEnvironment.setProperty("target.restUrl3","?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics");
        mockEnvironment.setProperty("target.response","{\"product\": {\"item\": {\"product_description\": {\"title\": \"BluRay Player\" } } } }");
        productDetailsService = new ProductDetailsServiceImpl(webClientMock);
    }


    @Test
    public void getProductName_validProductName_successful() throws Exception {
        String sampleProductName= "BluRay Player";
        ClientResponse mockClientResponse = mock(ClientResponse.class);
        when(mockClientResponse.statusCode()).thenReturn(HttpStatus.OK);
        when(mockClientResponse.bodyToMono(String.class)).thenReturn(Mono.just(mockEnvironment.getProperty("target.response")));
        when(webClientMock.getProductDetailsById("1")).thenReturn(Mono.just(mockClientResponse));
        String productName = productDetailsService.getProductName("1");
        assertEquals(productName,sampleProductName);
    }
    @Test
    public void getProductName_productNotFound_throwsProductNotFoundException() throws Exception {
        ClientResponse mockClientResponse = mock(ClientResponse.class);
        when(mockClientResponse.statusCode()).thenReturn(HttpStatus.OK);
        when(mockClientResponse.bodyToMono(String.class)).thenReturn(Mono.empty());
        when(webClientMock.getProductDetailsById(Mockito.anyString())).thenReturn(Mono.just(mockClientResponse));
       ProductNotFoundException ex = Assertions.assertThrows(ProductNotFoundException.class, () -> {
            productDetailsService.getProductName("1");
        });
        assertEquals(ex.getMessage(),"Unable to retrieve product details from look up service. : 1");

    }

    @Test
    public void getProductName_jsonParseError_throwsCustomException() throws Exception {
        ClientResponse mockClientResponse = mock(ClientResponse.class);
        when(mockClientResponse.statusCode()).thenReturn(HttpStatus.OK);
        when(mockClientResponse.bodyToMono(String.class)).thenReturn(Mono.just("abcd"));
        when(webClientMock.getProductDetailsById(Mockito.anyString())).thenReturn(Mono.just(mockClientResponse));
        CustomException ex = Assertions.assertThrows(CustomException.class, () -> {
            productDetailsService.getProductName("1");
        });
        assertEquals(ex.getMessage(),"JsonProcessingException occurred while parsing response for abcd");
    }

}
