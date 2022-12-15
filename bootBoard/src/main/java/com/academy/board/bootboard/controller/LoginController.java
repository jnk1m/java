package com.academy.board.bootboard.controller;

import com.academy.board.bootboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public String getLoginForm() {
        return "loginForm";
    }


}
