package com.example.base.common.enummeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

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
    public static SimpleEnum fromId(int code) {
        for (SimpleEnum e : values()) {
            if (e.code == code) return e;
        }
        throw new IllegalStateException("in valid code for enum");
    }
}
