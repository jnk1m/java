package com.academy.certificate.service;

import com.academy.certificate.entity.Resident;
import com.academy.certificate.exception.ResidentNotFoundException;
import com.academy.certificate.repository.ResidentRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ResidentDetailService implements UserDetailsService {
    private final ResidentRepository residentRepository;

    public ResidentDetailService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Resident resident = residentRepository.findByUserId(username).orElseThrow(ResidentNotFoundException::new);

        return new User(username, resident.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }
}
