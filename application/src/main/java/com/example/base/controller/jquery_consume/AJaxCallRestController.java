package com.example.base.controller.jquery_consume;

import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //this dude only cares about templates
//@RestController //this dude never care about template
@RequestMapping("/ajax")
class AJaxCallRestController {

    @GetMapping
    public String showIndex() {
        return "jqueryConsume/index";
    }

    @GetMapping(value = "/get")
    public ResponseEntity<Data> getData(Data data) {
        return ResponseEntity.ok(data); //response entity is require otherwise controller looking for template
    }

    @Value
    static class Data {
        int id;
        String content;
    }
}
