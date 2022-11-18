package com.academy.nhnmartcs.controller;

import com.academy.nhnmartcs.domain.Inquiry;
import com.academy.nhnmartcs.domain.User;
import com.academy.nhnmartcs.repository.InquiryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/cs")
public class InquiryController {
    InquiryRepository inquiryRepository;

    public InquiryController(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    @ModelAttribute("inquiry")
    public Inquiry getInquiry(@Valid @ModelAttribute Inquiry inquiry, BindingResult bindingResult) {
        return new Inquiry(inquiry.getTitle(), inquiry.getCategory(), inquiry.getContent(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE),
                inquiry.getId(), false);
    }

    @GetMapping()
    public String getCustomerInquiryList(HttpServletRequest req, Model model) {
        User user = (User) req.getSession().getAttribute("LoginUser");
        model.addAttribute("inquiries", inquiryRepository.getInquiryList(user.getId()));

        return "customerInquiryList";
    }

    @GetMapping("/inquiry")
    public String getWriteInquiryForm() {
        return "writeInquiry";
    }

    @PostMapping("/inquiry")
    public String doWriteInquiry(Inquiry inquiry, Model model, HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("LoginUser");
        List<Inquiry> inquiryList = inquiryRepository.getInquiryList(user.getId());

        if (inquiryList == null) {
            inquiryList = new ArrayList<>();
        }

        inquiryList.add(inquiry);

        inquiryRepository.getInquiryMap().put(inquiry.getId(), inquiryList);

        model.addAttribute("inquiries", inquiryList);

        return "customerInquiryList";

    }


}
