package com.academy.nhnmartcs.controller;

import com.academy.nhnmartcs.domain.Inquiry;
import com.academy.nhnmartcs.domain.User;
import com.academy.nhnmartcs.exception.ValidationFailedException;
import com.academy.nhnmartcs.repository.InquiryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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



    @GetMapping()
    public String getCustomerInquiryList(HttpServletRequest req, Model model) {
        User user = (User) req.getSession().getAttribute("LoginUser");
        List<Inquiry> inquiryList = inquiryRepository.getInquiryList(user.getId());
        model.addAttribute("inquiries", inquiryRepository.getInquiryList(user.getId()));

        return "customerInquiryList";
    }

    @GetMapping("/inquiry")
    public String getWriteInquiryForm() {
        return "writeInquiry";
    }


    @PostMapping("/inquiry")
    public String doWriteInquiry(@Valid @ModelAttribute Inquiry inquiry,
                                 BindingResult bindingResult,
                                 Model model, HttpServletRequest req) throws ValidationFailedException {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        User user = (User) req.getSession().getAttribute("LoginUser");
        List<Inquiry> inquiryList = inquiryRepository.getInquiryList(user.getId());

        if (inquiryList == null) {
            inquiryList = new ArrayList<>();
        }

        Inquiry newInquiry = new Inquiry(inquiry.getTitle(), inquiry.getCategory(), inquiry.getComment(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_DATE),
                user.getId(), false);

        inquiryList.add(newInquiry);


        inquiryRepository.getInquiryMap().put(inquiry.getId(), inquiryList);

        System.out.println("!!!"+inquiryList.size());
        model.addAttribute("inquiries", inquiryList);

        return "customerInquiryList";

    }

    @ExceptionHandler(ValidationFailedException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleValidationFailedException(ValidationFailedException ex, Model model) {
        model.addAttribute("exception", ex);
        return "error";
    }


}
