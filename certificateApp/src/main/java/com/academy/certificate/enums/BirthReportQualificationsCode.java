package com.academy.certificate.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BirthReportQualificationsCode {
    부("부"),
    모("모"),
    호주("호주"),
    동거친족("동거친족"),
    기타("기타");

    private final String value;

    BirthReportQualificationsCode(String value) {
        this.value = value;
    }

    @JsonCreator
    public static BirthReportQualificationsCode from(String value) {
        for (BirthReportQualificationsCode code : BirthReportQualificationsCode.values()) {
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
