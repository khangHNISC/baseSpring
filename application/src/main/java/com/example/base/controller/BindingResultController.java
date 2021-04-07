package com.example.base.controller;

import com.example.base.service1.GetForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

/**
 * Created by khangld5 on Apr 07, 2021
 */
@Controller
class BindingResultController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/bindingResult")
    public String showForm(@ModelAttribute("form") GetForm form) {
        //this @ModelAttribute is required
        return "form";
    }

    @PostMapping("/bindingResult")
    public String checkPersonInfo(@Valid @ModelAttribute("form") GetForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        return "redirect:/results";
    }
}
