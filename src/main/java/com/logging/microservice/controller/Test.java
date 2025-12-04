package com.logging.microservice.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Test {

  @GetMapping("/hello")
  public String hello(){
     return "hello";
  }

}
