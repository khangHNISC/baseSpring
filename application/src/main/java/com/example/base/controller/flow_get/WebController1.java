package com.example.base.controller.flow_get;

import com.example.base.common.enummeration.SimpleEnum;
import com.example.base.service1.AdvanceGetForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Created by khangld5 on Apr 08, 2021
 */
@Controller
@RequestMapping("/flowGet")
public class WebController1 {

    @GetMapping
    public String showForm(@ModelAttribute("form") AdvanceGetForm form, Model model) {
        model.addAttribute("enums", SimpleEnum.values());
        return "flowGet/form";
    }

    @PostMapping
    public String checkPersonInfo(@Valid @ModelAttribute("form") AdvanceGetForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("enums", SimpleEnum.values());
            return "flowGet/form";
        }
        return "redirect:/results";
    }
}
