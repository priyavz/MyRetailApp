package com.myretail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.myretail.controller")
@ComponentScan("com.myretail.service")
@ComponentScan("com.myretail.common.dao")
@ComponentScan("com.myretail.common.exception")
@Slf4j
public class MyRetailApp {

    public static void main(String[] args) {
        SpringApplication.run(MyRetailApp.class, args);
        log.info("Application started successfully...");
    }

}
