package com.academy.certificate.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderCode {
    남("남"),
    여("여");

    private final String value;

    GenderCode(String value) {
        this.value = value;
    }

    @JsonCreator
    public static GenderCode from(String value) {
        for (GenderCode code : GenderCode.values()) {
            if (code.getValue().equals(value)) {
                return code;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
