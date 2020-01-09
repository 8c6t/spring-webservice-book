package com.hachicore.springwebservicebook.web;

import com.hachicore.springwebservicebook.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(String name, Integer amount) {
        return new HelloResponseDto(name, amount);
    }

}
