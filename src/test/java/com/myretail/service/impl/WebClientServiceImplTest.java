package com.myretail.service.impl;

import com.myretail.service.WebClientService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class WebClientServiceImplTest {
    MockEnvironment mockEnvironment;

    private WebClientService webClientService;

    public static MockWebServer mockBackEnd;


    /**
     * Setup for Mockito before any test run.
     */
    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        mockEnvironment = new MockEnvironment();

        if (System.getProperty("spring.profiles.active") != null) {
            mockEnvironment.setProperty("spring_profiles", System.getProperty("spring.profiles.active"));
        } else {
            mockEnvironment.setProperty("spring_profiles", "default");
        }
        mockEnvironment.setProperty("target.restUrl1", "https://redsky.target.com");
        mockEnvironment.setProperty("target.restUrl2", "/v3/pdp/tcin/");
        mockEnvironment.setProperty("target.restUrl3", "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics");
        mockEnvironment.setProperty("target.response","{\"product\": {\"item\": {\"product_description\": {\"title\": \"BluRay Player\" } } } }");

        webClientService = new WebClientServiceImpl(mockEnvironment);
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    public void getProductDetailsByProductId_productExists_success() throws InterruptedException {
        String baseUrl = "https://redsky.target.com/v3/pdp/tcin/" + "1" + "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
        mockBackEnd.enqueue(new MockResponse()
                .setBody(mockEnvironment.getProperty("target.response"))
                .addHeader("Content-Type", "application/json"));

        Mono<ClientResponse> productDetailsMono = webClientService.getProductDetailsById("1");

        StepVerifier.create(productDetailsMono)
                .expectNextMatches(productDetails -> productDetailsMono.block().bodyToMono(String.class).block()
                        .equals(mockEnvironment.getProperty("target.response")))
                .verifyComplete();

        RecordedRequest recordedRequest = mockBackEnd.takeRequest();

        assertEquals("GET", recordedRequest.getMethod());
    }
}
