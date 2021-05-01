package com.example.base.controller.thymeleaf_flow;

import com.example.base.common.annotation.CurrencyFormat;
import com.example.base.common.enummeration.CurrencyUnit;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
@RequestMapping("/thymeleaf")
class ThymeleafFlow {

    @GetMapping
    public String index(Model model){
        // remember to use {{}} for formatter to kick in
        model.addAttribute("vn", new MoneyInVndDTO());
        return "thymeleafFlow/index";
    }

    @Value
    static class MoneyInVndDTO {
        @CurrencyFormat(unit = CurrencyUnit.VND)
        BigDecimal money = new BigDecimal(10000);
    }

}
