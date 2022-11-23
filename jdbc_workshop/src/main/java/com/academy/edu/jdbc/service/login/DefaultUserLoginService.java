package com.academy.edu.jdbc.service.login;

import com.academy.edu.jdbc.exception.LoginFailException;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserLoginService implements UserLoginService {
    private final UserRepository userRepository;

    public DefaultUserLoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) throws LoginFailException {
        User userFromRepo = userRepository.findByUserName(username);
        checkMatchedPwd(password, userFromRepo.getPassword());

        return userFromRepo;
    }

    private void checkMatchedPwd(String password, String userPassword) throws LoginFailException {
        if (!password.equals(userPassword)) {
            throw new LoginFailException();
        }
    }


}
