package com.academy.board.bootboard.service;

import com.academy.board.bootboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        com.academy.board.bootboard.entity.User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("아이디 또는 비밀번호를 확인해 주세요."));
        return new User(user.getUsername(),user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString())));
    }


}
