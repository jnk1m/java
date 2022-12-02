package com.academy.certificate.entity;

import com.academy.certificate.config.RootConfig;
import com.academy.certificate.config.WebConfig;
import com.academy.certificate.repository.ResidentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class),
})
@Transactional
class ResidentTest {
    @Autowired
    ResidentRepository residentRepository;


    @Test
    void getResidentById() {
        Optional<Resident> resident = residentRepository.findById(8L);
        assertThat(resident.get().getResidentSerialNumber()).isEqualTo(8L);
    }

    @Test
    void existById() {
        assertThat(residentRepository.existsById(9L)).isFalse();
    }
}