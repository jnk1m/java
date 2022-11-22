package com.academy.edu.jdbc.web;

import com.academy.edu.jdbc.service.login.UserLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserLoginService userLoginService;

    public UserController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @GetMapping("/login")
    public String getLoginForm(){
        return "loginForm";
    }
//
//    @PostMapping("/login")
//    public String doLogin(@ModelAttribute User user){
//        int loginResult = loginService.loginByIdAndPwd(connection, user.getId(), user.getPassword());
//        if(loginResult == 1){
//            return "";
//        }
//    }



}
