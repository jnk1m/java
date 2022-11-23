package com.academy.edu.jdbc.web;

import com.academy.edu.jdbc.exception.LoginFailException;
import com.academy.edu.jdbc.service.login.DefaultUserLoginService;
import com.academy.edu.jdbc.service.login.User;
import com.academy.edu.jdbc.service.login.UserLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserLoginService userLoginService;

    public LoginController(DefaultUserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @GetMapping
    public String getLoginForm() {
        return "loginForm";
    }

    @PostMapping()
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpServletRequest request) throws LoginFailException {

        User user = userLoginService.login(username, password);

        HttpSession session = request.getSession(true);
        session.setAttribute("LoginUser", user);

        return "redirect:/course/courseList";

    }


}
