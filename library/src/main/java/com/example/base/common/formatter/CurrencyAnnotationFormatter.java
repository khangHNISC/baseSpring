package com.example.base.common.formatter;

import com.example.base.common.annotation.CurrencyFormat;
import com.example.base.common.enummeration.CurrencyUnit;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class CurrencyAnnotationFormatter implements AnnotationFormatterFactory<CurrencyFormat> {

    @Override
    public Set<Class<?>> getFieldTypes() {
        return new HashSet<>(Collections.singletonList(BigDecimal.class));
    }

    @Override
    public Printer<?> getPrinter(CurrencyFormat currencyFormat, Class<?> aClass) {
        return configureFormatterFrom(currencyFormat);
    }

    @Override
    public Parser<?> getParser(CurrencyFormat currencyFormat, Class<?> aClass) {
        return configureFormatterFrom(currencyFormat);
    }

    private Formatter<BigDecimal> configureFormatterFrom(CurrencyFormat annotation) {
        CurrencyUnit unit = annotation.unit();
        return new LocaleCurrencyFormatter(unit.getLangCode(), unit.getCountryCode());
    }
}
