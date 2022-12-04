package com.academy.certificate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/certifications")
public class CertificationController {
    @GetMapping()
    public ModelAndView getCertificationPage(@RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("certificationPage");

        return modelAndView;
    }

}
