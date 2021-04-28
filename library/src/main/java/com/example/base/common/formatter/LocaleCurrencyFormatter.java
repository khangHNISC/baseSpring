package com.example.base.common.formatter;

import lombok.RequiredArgsConstructor;
import org.springframework.format.Formatter;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@RequiredArgsConstructor
public class LocaleCurrencyFormatter implements Formatter<BigDecimal> {
    //can be test

    private final String langCode;
    private final String countryCode;

    @Override
    public BigDecimal parse(String s, Locale locale) {
        return new BigDecimal(s);
    }

    @Override
    public String print(BigDecimal bigDecimal, Locale locale) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale(langCode, countryCode));
        return currencyFormatter.format(bigDecimal);
    }
}
