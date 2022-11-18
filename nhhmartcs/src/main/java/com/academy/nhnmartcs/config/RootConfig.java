package com.academy.nhnmartcs.config;

import com.academy.nhnmartcs.Base;
import com.academy.nhnmartcs.domain.Admin;
import com.academy.nhnmartcs.domain.Customer;
import com.academy.nhnmartcs.repository.UserRepository;
import com.academy.nhnmartcs.repository.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
        excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {
    @Bean
    public UserRepository userRepository() {
        UserRepository userRepository = new UserRepositoryImpl();
        userRepository.getUserMap().put("user1",new Customer("user1","12345","Smith",false));
        userRepository.getUserMap().put("admin",new Admin("admin","123","Admin",true));

        return userRepository;
    }
}
