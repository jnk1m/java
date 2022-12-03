package com.academy.certificate.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BirthPlaceCode {
    자택("자택"),
    병원("병원"),
    기타("기타");

    private final String value;

    BirthPlaceCode(String value) {
        this.value = value;
    }

    @JsonCreator
    public static BirthPlaceCode from(String value) {
        for (BirthPlaceCode code : BirthPlaceCode.values()) {
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
