package com.myretail.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductDetailsServiceImplTest {

    MockEnvironment environment = new MockEnvironment();
    @Mock Environment env;
    @Mock
    private WebClient webClientMock;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersMock;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriMock;
    @Mock
    private WebClient.RequestBodySpec requestBodyMock;
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriMock;
    @Mock
    private WebClient.ResponseSpec responseMock;
    WebClientServiceImpl myRetailService = new WebClientServiceImpl(environment);


    @Before
    public void init(){
        env = mock(Environment.class);

        when(env.getProperty("target.restUrl1"))
                .thenReturn("http://redsky.target.com");
        when(env.getProperty("target.restUrl2"))
                .thenReturn("/v2/pdp/tcin/");
        when(env.getProperty("target.restUrl3"))
                .thenReturn("?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics");

        webClientMock = WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl( env.getProperty("target.restUrl1"))
                .build();

    }
    @Test
    public void getProductById() throws Exception {
        String mockResponse = "";

       // when(webClientMock.get().retrieve()).thenReturn(requestHeadersUriMock);
//        when(requestHeadersUriMock.uri(env.getProperty("target.restUrl2") + "1" + env.getProperty("target+restUrl3"))).thenReturn(requestBodyMock);
//        when(requestHeadersUriMock.uri(env.getProperty("target.restUrl2") + "1" + env.getProperty("target+restUrl3"))).thenReturn(requestHeadersMock);
        when(webClientMock.get().uri(env.getProperty("target.restUrl2") + "1" + env.getProperty("target+restUrl3")).retrieve().bodyToMono(String.class)).thenReturn(Mono.just(mockResponse));
      //  when(responseMock.bodyToMono(String.class)).thenReturn(Mono.just(mockResponse));

      //  Mono<String> responseBody = myRetailService.getProductById("1");

//        assertEquals(responseBody.block(Duration.ofMillis(100)),"");
//        webClientMock.get().uri(env.getProperty("target.restUrl2") + "1" + env.getProperty("target+restUrl3"))
//                .retrieve()
//                .expectStatus().isOk()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
//                .expectBodyList(String.class);
    }
}
