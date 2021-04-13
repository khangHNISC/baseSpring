package com.example.base.common.config;

import com.example.base.common.conversion.StringToEnumConverter;
import com.example.base.common.conversion.ValueToEnumConverter;
import com.example.base.common.conversion.WithValue;
import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by khangld5 on Apr 08, 2021
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new ConverterFactory<String, Enum<?>>() {
            @Override
            public <T extends Enum<?>> @NonNull Converter<String, T> getConverter(@NonNull Class<T> targetType) {
                return WithValue.class.isAssignableFrom(targetType) ?
                        new ValueToEnumConverter<>(targetType)
                        : new StringToEnumConverter<>(targetType);
            }
        });
    }
}
