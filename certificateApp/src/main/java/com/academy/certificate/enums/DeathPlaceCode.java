package com.academy.certificate.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DeathPlaceCode {
    주택("주택"),
    의료기관("의료기관"),
    사회복지시설("사회복지시설"),
    산업장("산업장"),
    공공시설("공공시설"),
    도로("도로"),
    상업서비스시설("상업서비스시설"),
    농장("농장"),
    병원_이송_중_사망("병원 이송 중 사망"),
    기타("기타");

    private final String value;

    DeathPlaceCode(String value) {
        this.value = value;
    }

    @JsonCreator
    public static DeathPlaceCode from(String value) {
        for (DeathPlaceCode code : DeathPlaceCode.values()) {
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
