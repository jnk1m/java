package com.academy.jdbc.board.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        Object loginUser = session.getAttribute("LoginUser");

        if (Objects.isNull(loginUser)) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
