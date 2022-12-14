package com.academy.board.bootboard.service;

import com.academy.board.bootboard.domain.UserCreateRequest;
import com.academy.board.bootboard.entity.User;
import com.academy.board.bootboard.exception.LoginFailException;
import com.academy.board.bootboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUser(String username) throws LoginFailException {
        return userRepository.findByUsername(username).orElseThrow(LoginFailException::new);
    }

    @Override
    public User createUser(UserCreateRequest userCreateRequest) {
        User user = new User(userCreateRequest.getUsername(), passwordEncoder.encode(userCreateRequest.getPassword()));
        userRepository.save(user);
        return user;
    }


}
