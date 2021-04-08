package com.example.base.common.conversion;

import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;

import java.util.EnumSet;

/**
 * Created by khangld5 on Apr 08, 2021
 */
@SuppressWarnings("unchecked")
public final class ValueToEnumConverter<T extends Enum> implements Converter<String, T> {

    private final Class<T> enumType;

    public ValueToEnumConverter(Class<T> enumType) {
        this.enumType = enumType;
    }

    public T convert(@NonNull String source) {
        for (Object e : EnumSet.allOf(enumType)) {
            if (e instanceof WithValue && ((WithValue) e).withValue() == Integer.parseInt(source)) {
                return (T) e;
            }
        }
        return null;
    }
}
