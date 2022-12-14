package com.academy.board.bootboard.controller;

import com.academy.board.bootboard.entity.User;
import com.academy.board.bootboard.exception.LoginFailException;
import com.academy.board.bootboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping("/login")
    public String getLoginForm(){
        return "loginForm";
    }

//    @PostMapping("/login")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpServletRequest request) throws LoginFailException{


        User user = userService.getUser(username);

        if(!user.getPassword().equals(password)){
            throw new LoginFailException();
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("LoginUser", user);

        return "redirect:/posts";
    }

    @GetMapping("/logout")
    public String doLogout(HttpServletRequest request) {
        request.getSession(false).removeAttribute("LoginUser");
        request.getSession(false).invalidate();
        return "redirect:/posts";
    }

}
