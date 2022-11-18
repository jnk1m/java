package com.academy.nhnmartcs.domain;

import java.util.Arrays;

public enum InquiryCategory {
    COMPLAIN("불만 접수"),
    SUGGESTION("제안"),
    REFUNDOREXCHANGE("환불/교환"),
    COMPLIMENT("칭찬해요"),
    ETC("기타 문의");

    final private String name;
    private InquiryCategory(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

//    public static InquiryCategory nameOf(String name) {
//        for (InquiryCategory status : InquiryCategory.values()) {
//            if (status.getName().equals(name)) {
//                return status;
//            }
//        }
//        return null;
//    }

}
