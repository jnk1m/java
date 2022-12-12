package com.academy.board.bootboard.service;

import com.academy.board.bootboard.entity.User;
import com.academy.board.bootboard.exception.LoginFailException;
import com.academy.board.bootboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUser(String username) throws LoginFailException {
        return userRepository.findByUsername(username).orElseThrow(LoginFailException::new);
    }

}
