package com.academy.board.bootboard.controller;

import com.academy.board.bootboard.domain.UserCreateRequest;
import com.academy.board.bootboard.entity.User;
import com.academy.board.bootboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.ValidationException;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String getSignUp(){
        return "signup";
    }

    @PostMapping("/signup")
    public String doSignUp(@Validated @ModelAttribute UserCreateRequest userCreateRequest,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException();
        }
        User user = userService.createUser(userCreateRequest);
        return "redirect:/login";
    }
}
