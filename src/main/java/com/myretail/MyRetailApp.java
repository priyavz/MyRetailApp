package com.myretail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@ComponentScan("com.myretail.controller")
@ComponentScan("com.myretail.service")
@ComponentScan("com.myretail.dao")
public class MyRetailApp {
    private static final Logger log = LoggerFactory.getLogger(MyRetailApp.class);

    public static void main(String[] args) {
        SpringApplication.run(MyRetailApp.class, args);
    }


}
