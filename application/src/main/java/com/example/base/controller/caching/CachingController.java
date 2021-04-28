package com.example.base.controller.caching;

import com.example.base.caching.CachingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/caching")
class CachingController {

    private final @NotNull CachingService service;

    @ResponseBody
    @GetMapping("/get/{id}")
    //Caching previous id as well
    public String getDto(@PathVariable String id) throws InterruptedException {
        return service.getDTO(id).getName();
    }
}
