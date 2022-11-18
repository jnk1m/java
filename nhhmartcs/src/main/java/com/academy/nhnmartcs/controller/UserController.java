package com.academy.nhnmartcs.controller;

import com.academy.nhnmartcs.domain.User;
import com.academy.nhnmartcs.repository.UserRepository;
import com.academy.nhnmartcs.repository.UserRepositoryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cs")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepositoryImpl userRepositoryImpl) {
        this.userRepository = userRepositoryImpl;
    }

    @ModelAttribute("user")
    public User getUser(@PathVariable("id") String id) {
        if (!studentRepository.exists(studentId)) {
            throw new StudentNotExistException(studentId);
        }
        return studentRepository.getStudent(studentId);
    }

    @GetMapping("/login")
    public String getLoginForm(){
        return "loginForm";
    }

    @PostMapping("/login")
    public String doLogin(){

    }

}
