package com.example.base.controller.jqueryConsume;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AJaxCallRestController.class)
class AJaxCallRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @SneakyThrows
    void testAjaxGetCall() {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("id", "10");
        requestParams.add("content", "OI GIOI OI");
        this.mvc.perform(get("/ajax/get")
                .params(requestParams))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("OI GIOI OI"));
    }
}