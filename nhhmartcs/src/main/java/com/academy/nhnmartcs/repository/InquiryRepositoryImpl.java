package com.academy.nhnmartcs.repository;

import com.academy.nhnmartcs.domain.Inquiry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InquiryRepositoryImpl implements InquiryRepository{
    Map<String, List<Inquiry>> inquiryMap = new HashMap<>();

    @Override
    public Map<String, List<Inquiry>> getInquiryMap() {
        return inquiryMap;
    }

    @Override
    public List<Inquiry> getInquiryList(String id) {
        if(inquiryMap.get(id)==null){
            return null;
        }
        return inquiryMap.get(id);
    }


}
