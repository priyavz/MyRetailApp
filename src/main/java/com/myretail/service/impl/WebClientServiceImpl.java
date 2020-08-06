package com.myretail.service.impl;

import com.myretail.service.WebClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

@Component
@Slf4j
public class WebClientServiceImpl implements WebClientService {

    private final Environment env;

    @Autowired
    public WebClientServiceImpl(Environment env) {
        this.env = env;
    }

    public Mono<ClientResponse> getProductDetailsById(String productId) {
        log.debug("productId={} msg=About to make http request for product", productId);

        String baseUrl = env.getProperty("target.prodcut.lookup.base.url");
        String endpoint = env.getProperty("target.product.lookup.endpoint");
        String lookupUrl = format(endpoint, productId);

        log.debug("productId={} msg=About to do a product lookup. url={}", productId, lookupUrl);

        return WebClient.create(baseUrl)
                        .get()
                        .uri(lookupUrl)
                        .exchange();
    }

}
