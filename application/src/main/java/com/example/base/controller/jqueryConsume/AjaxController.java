package com.example.base.controller.jqueryConsume;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //this dude only cares about templates
class AjaxController {
    @GetMapping("/ajax")
    public String showIndex() {
        return "jqueryConsume/index";
    }
}
