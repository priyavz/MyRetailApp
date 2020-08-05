package com.myretail.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.myretail.exception.GlobalMyRetailExceptionHandler;
import com.myretail.exception.ProductNotFoundException;
import com.myretail.model.ProductData;
import com.myretail.service.ProductDataService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientException;

@RestController
@RequestMapping("/myRetail/v1")
public class MyRetailController {

    private static final Logger log = LoggerFactory.getLogger(MyRetailController.class);

    private final ProductDataService productDataService;

    @Autowired
    public MyRetailController(ProductDataService productDataService) {
        this.productDataService = productDataService;
    }

    @RequestMapping(value="product/{id}",method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler({ProductNotFoundException.class,JsonProcessingException.class,WebClientException.class})
    @ResponseBody
    public ResponseEntity<ProductData> getProductDetails(@PathVariable String id) throws JsonProcessingException,WebClientException,ProductNotFoundException {
        return new ResponseEntity<ProductData>( productDataService.getProductDataById(id), HttpStatus.OK);
    }
}
