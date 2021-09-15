package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@SpringBootApplication
@RestController
class DemoApplication {

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
        SpringApplication.run(DemoApplication.class,args);
    }

    @RequestMapping("/query")
    public String[] query(String[]types){
        return types;

    }





}