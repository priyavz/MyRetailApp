package com.myretail.service.impl;

import com.myretail.service.WebClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebClientServiceImpl implements WebClientService {
    private static final Logger log = LoggerFactory.getLogger(WebClientServiceImpl.class);

    private final Environment env;

    private WebClient webClient;

    @Autowired
    public WebClientServiceImpl(Environment env) {
        this.env = env;
    }

    public Mono<ClientResponse> getProductDetailsById(String productId) {
        log.debug("{} About to make http request for product with productId {}  ",Thread.currentThread().getId(),productId);
        webClient = WebClient.create(env.getProperty("target.restUrl1"));
        return webClient.get().uri(env.getProperty("target.restUrl2") + productId + env.getProperty("target.restUrl3"))
                .exchange();
    }
}
