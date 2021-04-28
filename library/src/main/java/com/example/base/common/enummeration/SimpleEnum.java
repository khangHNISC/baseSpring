package com.example.base.common.enummeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

/**
 * Created by khangld5 on Apr 08, 2021
 */
@Getter
@ToString
@AllArgsConstructor
public enum SimpleEnum {
    ALL(0, "--ALL--"),
    SIMPLE1(1, "SIMPLE1");

    int code;
    String description;

    @SuppressWarnings("unused")
    public static SimpleEnum detect(Integer code) {
        Assert.notNull(code, "Code must not be null");
        for (SimpleEnum candidate : values()) {
            if (candidate.code == code) return candidate;
        }
        throw new IllegalArgumentException("'" + code + "' is not a valid code");
    }
}
