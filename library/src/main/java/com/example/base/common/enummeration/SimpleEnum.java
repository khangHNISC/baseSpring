package com.example.base.common.enummeration;

import com.example.base.common.conversion.WithValue;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by khangld5 on Apr 08, 2021
 */
@Getter
@ToString
public enum SimpleEnum implements WithValue {
    ALL(0, "--ALL--"),
    SIMPLE1(1, "SIMPLE1");

    int code;
    String description;

    SimpleEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public int withValue() {
        return code;
    }
}
