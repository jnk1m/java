package com.academy.nhnmartcs.controller;

import com.academy.nhnmartcs.domain.Inquiry;
import com.academy.nhnmartcs.domain.User;
import com.academy.nhnmartcs.repository.InquiryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cs")
public class InquiryController {
    InquiryRepository inquiryRepository;

    public InquiryController(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    @GetMapping()
    public String getCustomerInquiryList(HttpServletRequest req, Model model) {
        User user = (User)req.getSession().getAttribute("LoginUser");
        List<Inquiry> inquiryList = inquiryRepository.getInquiryList(user.getId());
        model.addAttribute("inquiries",inquiryRepository.getInquiryList(user.getId()));

        return "customerInquiryList";
    }


}
