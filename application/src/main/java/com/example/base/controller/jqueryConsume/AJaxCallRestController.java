package com.example.base.controller.jqueryConsume;

import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //this dude never care about template
@RequestMapping("/ajaxCall")
class AJaxCallRestController {

    @GetMapping(value = "/get")
    public Data getData(Data data) {
        return data;
    }

    @Value
    static class Data {
        int id;
        String content;
    }
}
