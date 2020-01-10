package com.hachicore.springwebservicebook.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("hello가 리턴된다")
    void getHello() throws Exception {
        String hello = "hello";

        mockMvc.perform(get("/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(hello))
        ;
    }

    @Test
    @DisplayName("helloDto가 리턴된다")
    void getHelloDto() throws Exception {
        String name = "hello";
        Integer amount = 1000;

        mockMvc.perform(get("/hello/dto")
                    .param("name", name)
                    .param("amount", String.valueOf(amount)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(name))
                .andExpect(jsonPath("amount").value(amount))
        ;
    }

}
