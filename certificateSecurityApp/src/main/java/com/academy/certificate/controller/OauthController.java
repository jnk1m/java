package com.academy.certificate.controller;

import com.academy.certificate.domain.GithubEmail;
import com.academy.certificate.domain.GithubResponse;
import com.academy.certificate.service.ResidentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.UUID;

@Controller
public class OauthController {
    private UriComponents uriComponents;
    private final ResidentService residentService;
    private final RestTemplate restTemplate = new RestTemplate();

    public OauthController(ResidentService residentService) {
        this.residentService = residentService;
    }

    String clientId = "2f7a621dfd6cebf349d9";
    String clientSecret = "d08bcdbb5b735b4e7d88fe1b5d64d492cbdbd896";


    @GetMapping("/github/login")
    public String getGithubLoin() {
        uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("github.com")
                .path("/login/oauth/authorize")
                .queryParam("client_id", clientId)
                .queryParam("scope", "user:email")
                .build();

        return "redirect:" + uriComponents.toUriString();

    }


    @GetMapping("/login/oauth2/code/github")
    public String getEmail(@RequestParam("code") String code, HttpServletRequest request) {
        uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("github.com")
                .path("/login/oauth/access_token")
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("code", code)
                .build();

        GithubResponse githubResponse = restTemplate.getForObject(uriComponents.toUri(), GithubResponse.class);

        String email = getEmailFromGithub(githubResponse);
        User user = getUserWithEmail(email);
        putUserToSecurityContext(user);

        return "redirect:/residents";
    }


    private String getEmailFromGithub(GithubResponse githubResponse) {
        uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.github.com")
                .path("/user")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(githubResponse.getAccessToken());
        HttpEntity<GithubEmail> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<GithubEmail> emailResponseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, httpEntity, GithubEmail.class);
        return emailResponseEntity.getBody().getEmail();
    }

    private User getUserWithEmail(String email) {
        return residentService.getResidentByEmail(email);
    }

    private UsernamePasswordAuthenticationToken putUserToSecurityContext(User user) {


        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user, null, Collections.singletonList(new SimpleGrantedAuthority("USER")));

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        return usernamePasswordAuthenticationToken;
    }

//    private void loginSuccessHandle(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken,
//                                    HttpServletRequest request) {
//        String sessionId = UUID.randomUUID().toString();
//        System.out.println("sessionId 2 = " + sessionId);
//
//        UserDetails userDetails = (UserDetails) usernamePasswordAuthenticationToken.getPrincipal();
//
//        HttpSession session = request.getSession();
//        session.setAttribute("sessionId", sessionId);
//        session.setAttribute("authority", userDetails.getAuthorities());
//
//
//    }


}
