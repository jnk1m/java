package com.academy.certificate.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FamilyRelationshipCode {
    부("Father"),
    모("Mother"),
    자녀("Child"),
    배우자("Spouse");

    private final String value;

    FamilyRelationshipCode(String value) {
        this.value = value;
    }

    @JsonCreator
    public static FamilyRelationshipCode from(String value) {
        for (FamilyRelationshipCode code : FamilyRelationshipCode.values()) {
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

