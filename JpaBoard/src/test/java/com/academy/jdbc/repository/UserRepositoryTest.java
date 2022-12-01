package com.academy.jdbc.repository;

import com.academy.jdbc.config.RootConfig;
import com.academy.jdbc.config.WebConfig;
import com.academy.jdbc.entity.Role;
import com.academy.jdbc.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class UserRepositoryTest {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void test1() {
        Optional<User> user = userRepository.findById(1);
        assertThat(user.get().getUserId()).isEqualTo(1);

        List<User> all = userRepository.findAll();
        assertThat(all.size()).isEqualTo(3);
    }

    @Test
    void exist_fail_Test() {
        Role role = new Role(1, "admin");
        User user = new User(1, "test", "test1", role);

        boolean existsByUsername = userRepository.existsByUsername(user.getUsername());
        assertThat(existsByUsername).isFalse();
    }

    @Test
    void save_success_Test() {
        Role role = new Role(1, "admin");
        User user = new User(3, "test", "test1", role);
        userRepository.saveAndFlush(user);

        assertThat(userRepository.existsByUsername(user.getUsername())).isTrue();
    }
}