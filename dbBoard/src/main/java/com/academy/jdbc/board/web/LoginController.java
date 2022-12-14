package com.academy.jdbc.board.web;

import com.academy.jdbc.board.domain.User;
import com.academy.jdbc.board.exception.LoginFailException;
import com.academy.jdbc.board.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@Slf4j
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }
    /*로그인 폼 불러오기*/
    @GetMapping("/login")
    public String getLoginForm() {
        return "loginForm";
    }

    /*로그인하기*/
    @PostMapping("/login")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpServletRequest request) throws LoginFailException {
        Optional<User> user = userService.login(username, password);

        HttpSession session = request.getSession(true);

        session.setAttribute("LoginUser", user);

        return "redirect:/community/list";
    }

    /*로그아웃하기*/
    @GetMapping("/logout")
    public String doLogout(HttpServletRequest request) {
        request.getSession(false).removeAttribute("LoginUser");
        request.getSession(false).invalidate();
        return "redirect:/login";
    }


}
