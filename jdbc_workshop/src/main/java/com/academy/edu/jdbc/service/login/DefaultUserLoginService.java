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
    public User login(User user) throws LoginFailException {
        User userFromRepo = userRepository.findById(user.getId());
        checkUserWasFound(userFromRepo);
        checkMatchedPwd(user.getPassword(), userFromRepo.getPassword());

        return userFromRepo;
    }

    private void checkUserWasFound(User userFromRepo) throws LoginFailException {
        if (userFromRepo == null) {
            throw new LoginFailException();
        }
    }

    private void checkMatchedPwd(String password, String userPassword) throws LoginFailException {
        if (!password.equals(userPassword)) {
            throw new LoginFailException();
        }
    }


}
