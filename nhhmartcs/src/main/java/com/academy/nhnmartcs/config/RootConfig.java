package com.academy.nhnmartcs.config;

import com.academy.nhnmartcs.Base;
import com.academy.nhnmartcs.domain.Authorization;
import com.academy.nhnmartcs.domain.Inquiry;
import com.academy.nhnmartcs.domain.InquiryCategory;
import com.academy.nhnmartcs.domain.User;
import com.academy.nhnmartcs.repository.InquiryRepository;
import com.academy.nhnmartcs.repository.InquiryRepositoryImpl;
import com.academy.nhnmartcs.repository.UserRepository;
import com.academy.nhnmartcs.repository.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
        excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {
    @Bean
    public UserRepository userRepository() {
        UserRepository userRepository = new UserRepositoryImpl();
        userRepository.getUserMap().put("user1",new User("user1","12345","Smith", Authorization.CUSTOMER));
        userRepository.getUserMap().put("admin",new User("admin","123","Admin",Authorization.ADMIN));

        return userRepository;
    }

    @Bean
    public InquiryRepository inquiryRepository(){
        InquiryRepository inquiryRepository = new InquiryRepositoryImpl();
        List<Inquiry> inquiryList = new ArrayList<>();

        inquiryList.add(new Inquiry("제목", InquiryCategory.COMPLIMENT,"test",
                LocalDateTime.now().withNano(0),"user1",false));

        inquiryRepository.getInquiryMap().put("user1",inquiryList);
        return inquiryRepository;
    }
}
