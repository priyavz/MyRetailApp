package com.myretail.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.common.exception.CustomException;
import com.myretail.common.exception.ProductNotFoundException;
import com.myretail.service.ProductDetailsService;
import com.myretail.service.WebClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientException;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

@Component
public class ProductDetailsServiceImpl implements ProductDetailsService {
    private static final Logger log = LoggerFactory.getLogger(ProductDetailsServiceImpl.class);

    private final WebClientService webClientService;

    @Autowired
    public ProductDetailsServiceImpl(WebClientServiceImpl webClientService) {
        this.webClientService = webClientService;
    }

    @Override
    public String getProductName(String productId) throws CustomException, WebClientException, ProductNotFoundException {
        Optional<String> webClientResponse = webClientService.getProductDetailsById(productId).block().bodyToMono(String.class).blockOptional(Duration.ofMillis(1000));
        if(!webClientResponse.isPresent()) throw new ProductNotFoundException(productId, "Unable to retrieve product details from look up service.");
        return extractNameFromResponse(webClientResponse.get()).get();
    }

    private Optional<String> extractNameFromResponse(String webClientResponse) throws CustomException {
        log.debug("Extracting product name from webclientResponse {}  ", webClientResponse);
        try {
            ObjectMapper mapper = new ObjectMapper();
            @SuppressWarnings("rawtypes")
            Map<String, Map> webClientResponseMap;
            webClientResponseMap = mapper.readValue(webClientResponse, Map.class); // Get JSON
            Map<String, Map> productMap = webClientResponseMap.get("product");
            Map<String, Map> itemMap = productMap.get("item"); // For Mapping
            Map<String, String> productDescriptionMap = itemMap.get("product_description");
            return Optional.ofNullable(productDescriptionMap.get("title"));
        } catch(JsonProcessingException ex) {
            throw new CustomException("JsonProcessingException occurred while parsing response for ",webClientResponse);
        }
    }
}
