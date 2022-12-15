package com.academy.board.bootboard.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    public RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        String sessionId = UUID.randomUUID().toString();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        HttpSession session = request.getSession();

        session.setAttribute("sessionId", sessionId);
        session.setAttribute("userId", userDetails.getUsername());
        session.setAttribute("authority", userDetails.getAuthorities());


        redirectStrategy.sendRedirect(request, response, "/posts");

    }
}
