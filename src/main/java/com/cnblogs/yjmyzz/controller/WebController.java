package com.cnblogs.yjmyzz.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WebController {


    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring AI!";
    }


}