package com.academy.certificate.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BirthDeathTypeCode {
    출생("출생"),
    사망("사망");

    private final String value;

    BirthDeathTypeCode(String value) {
        this.value = value;
    }

    @JsonCreator
    public static BirthDeathTypeCode from(String value) {
        for (BirthDeathTypeCode code : BirthDeathTypeCode.values()) {
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
