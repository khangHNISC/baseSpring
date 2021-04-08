package com.example.base.common.conversion;

import org.springframework.core.convert.converter.Converter;

/**
 * Created by khangld5 on Apr 08, 2021
 */
@SuppressWarnings("unchecked")
public final class StringToEnumConverter<T extends Enum> implements Converter<String, T> {

    private final Class<T> enumType;

    public StringToEnumConverter(Class<T> enumType) {
        this.enumType = enumType;
    }

    public T convert(String source) {
        return (T) Enum.valueOf(this.enumType, source.trim());
    }
}
