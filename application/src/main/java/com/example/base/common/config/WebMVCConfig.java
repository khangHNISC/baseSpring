package com.example.base.common.config;

import com.example.base.common.conversion.ConditionalEnumConverter;
import com.example.base.common.formatter.CurrencyAnnotationFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by khangld5 on Apr 08, 2021
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(new CurrencyAnnotationFormatter());
        registry.addConverter(new ConditionalEnumConverter());
        //registry.addConverterFactory(new EnumConverterFactory());
    }
}
