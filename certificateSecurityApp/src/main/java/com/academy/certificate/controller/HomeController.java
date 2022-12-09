package com.academy.certificate.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping("/m")
    public void home(HttpSession session){

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) a.getPrincipal();
        String username = userDetails.getUsername();


    }
}
