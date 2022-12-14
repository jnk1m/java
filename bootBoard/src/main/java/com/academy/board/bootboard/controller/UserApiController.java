package com.academy.board.bootboard.controller;

import com.academy.board.bootboard.domain.UserCreateRequest;
import com.academy.board.bootboard.entity.User;
import com.academy.board.bootboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserApiController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> CreateUser(@RequestBody UserCreateRequest userCreateRequest,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        User user = userService.createUser(userCreateRequest);
        return ResponseEntity.ok(user);
    }
}
