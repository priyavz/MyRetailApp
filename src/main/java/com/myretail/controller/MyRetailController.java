package com.myretail.controller;

import com.myretail.common.exception.CustomException;
import com.myretail.service.ProductDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public ResponseEntity<Object> getProductDetails(@PathVariable String id) throws CustomException {
        return new ResponseEntity<>( productDataService.getProductDataById(id), HttpStatus.OK);
    }

}
