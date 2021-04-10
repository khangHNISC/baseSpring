package com.example.base.common.conversion;

import com.example.base.common.annotation.EnumFromId;
import lombok.SneakyThrows;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class ConditionalEnumConverter implements ConditionalGenericConverter {
    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return targetType.hasAnnotation(EnumFromId.class) &&
                Objects.requireNonNull(targetType.upcast(Enum.class)).getType() == Enum.class;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, Enum.class));
    }

    @Override
    @SneakyThrows
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        int code = Integer.parseInt((String) source);
        Class<?> clazz = Class.forName(targetType.getName());
        Method fromId = clazz.getMethod("fromId", int.class);
        return fromId.invoke(null, code);
    }
}
