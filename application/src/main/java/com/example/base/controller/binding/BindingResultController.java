package com.example.base.controller.binding;

import com.example.base.service1.PostForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class BindingResultController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/binding")
    public String showForm(@ModelAttribute("form") PostForm postForm) {
        //@ModelAttribute = model.addAttribute("form", new PostForm())
        return "binding/form";
    }

    //this can apply to get mapping
    @PostMapping("/binding")
    public String checkPersonInfo(@Valid @ModelAttribute("form") PostForm postForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "binding/form";
        }
        return "redirect:/results";
    }
}
