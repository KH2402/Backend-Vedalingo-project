package com.vedalingo.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @CrossOrigin(origins = "http://localhost:8080" , methods = RequestMethod.GET)
    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("test--");
        return "Hello, world!";
    }
}
