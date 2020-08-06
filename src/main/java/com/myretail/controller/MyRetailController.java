package com.myretail.controller;

import com.myretail.common.exception.CustomException;
import com.myretail.common.exception.ProductNotFoundException;
import com.myretail.common.model.ProductData;
import com.myretail.service.ProductDataService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/myRetail/v1")
@Slf4j
public class MyRetailController {

    private final ProductDataService productDataService;

    @Autowired
    public MyRetailController(ProductDataService productDataService) {
        this.productDataService = productDataService;
    }

    @RequestMapping(value = "product/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ProductData> getProductDetails(@PathVariable String id) throws ProductNotFoundException, CustomException {
        MDC.put("uuid", id + "-" + UUID.randomUUID().toString());
        return new ResponseEntity<>(productDataService.getProductDataById(id), HttpStatus.OK);
    }

}
