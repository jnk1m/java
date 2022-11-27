package com.academy.jdbc.board.interceptor;

import com.academy.jdbc.board.domain.User;
import com.academy.jdbc.board.exception.NoPermissionException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AdminCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Optional<User> user = (Optional<User>) request.getSession().getAttribute("LoginUser");
        if (user.get().getRole() != 1) {
            throw new NoPermissionException();
        }
        return true;
    }
}
