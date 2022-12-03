package com.academy.certificate.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DeathReportQualificationsCode {
    동거친족("동거친족"),
    비동거친족("비동거친족"),
    동거자("동거자"),
    기타("기타");

    private final String value;

    DeathReportQualificationsCode(String value) {
        this.value = value;
    }

    @JsonCreator
    public static DeathReportQualificationsCode from(String value) {
        for (DeathReportQualificationsCode code : DeathReportQualificationsCode.values()) {
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
