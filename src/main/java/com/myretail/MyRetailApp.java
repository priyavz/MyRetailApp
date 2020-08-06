package com.myretail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.myretail.controller")
@ComponentScan("com.myretail.service")
@ComponentScan("com.myretail.dao")
@ComponentScan("com.myretail.exception")
public class MyRetailApp {
    private static final Logger log = LoggerFactory.getLogger(MyRetailApp.class);

    public static void main(String[] args) {
        SpringApplication.run(MyRetailApp.class, args);
    }


}
