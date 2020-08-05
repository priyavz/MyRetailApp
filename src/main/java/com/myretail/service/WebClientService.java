package com.myretail.service;

import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

public interface WebClientService {

    Mono<ClientResponse> getProductDetailsById(String productId);

}
