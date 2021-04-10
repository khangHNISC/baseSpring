package com.example.base.common.conversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * implement the target enum with @{WithValue} to achieve the desire
 */
@SuppressWarnings("unused")
public class EnumConverterFactory implements ConverterFactory<String, Enum<?>> {
    @Override
    public <T extends Enum<?>> Converter<String, T> getConverter(Class<T> targetType) {
        return WithValue.class.isAssignableFrom(targetType) ?
                new ValueToEnumConverter<>(targetType)
                : new StringToEnumConverter<>(targetType);
    }
}
