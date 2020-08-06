package com.myretail.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.common.exception.CustomException;
import com.myretail.common.exception.ProductNotFoundException;
import com.myretail.service.ProductDetailsService;
import com.myretail.service.WebClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientException;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class ProductDetailsServiceImpl implements ProductDetailsService {

    private final WebClientService webClientService;

    @Autowired
    public ProductDetailsServiceImpl(WebClientServiceImpl webClientService) {
        this.webClientService = webClientService;
    }

    @Override
    public String getProductName(String productId) throws CustomException, WebClientException, ProductNotFoundException {
        Optional<String> webClientResponse = webClientService.getProductDetailsById(productId).block().bodyToMono(String.class).blockOptional(Duration.ofMillis(1000));

        if (!webClientResponse.isPresent())
            throw new ProductNotFoundException(productId, "Unable to retrieve product details from look up service.");

        return extractNameFromResponse(webClientResponse.get()).get();
    }

    @SuppressWarnings("rawtypes")
    private Optional<String> extractNameFromResponse(String webClientResponse) throws CustomException {
        log.debug("Extracting product name from webclientResponse {}  ", webClientResponse);

        try {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Map> webClientResponseMap = mapper.readValue(webClientResponse, Map.class); // Get JSON

            Map<String, Map> productMap = webClientResponseMap.get("product");
            Map<String, Map> itemMap = productMap.get("item"); // For Mapping
            Map<String, String> productDescriptionMap = itemMap.get("product_description");

            return Optional.ofNullable(productDescriptionMap.get("title"));
        } catch (JsonProcessingException ex) {
            throw new CustomException("JsonProcessingException occurred while parsing response for ", webClientResponse);
        }
    }
    
}
