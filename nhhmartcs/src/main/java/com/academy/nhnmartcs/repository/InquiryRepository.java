package com.academy.nhnmartcs.repository;

import com.academy.nhnmartcs.domain.Inquiry;

import java.util.List;
import java.util.Map;

public interface InquiryRepository {
    Map<String, List<Inquiry>> getInquiryMap();

    List<Inquiry> getInquiryList(String id);
}
