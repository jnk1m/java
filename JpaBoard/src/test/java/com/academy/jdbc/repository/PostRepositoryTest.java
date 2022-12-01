package com.academy.jdbc.repository;

import com.academy.jdbc.config.RootConfig;
import com.academy.jdbc.config.WebConfig;
import com.academy.jdbc.entity.Post;
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class),
})
@Transactional
class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Test
    void findByIdTest() {
        Optional<Post> post = postRepository.findById(3);
        assertThat(post.get().getPostId()).isEqualTo(3);
    }

    @Test
    void countByCreatedByTest() {
        Role role = new Role(1, "admin");
        User user = new User(1, "test", "test1", role);
        int i = postRepository.countByCreatedBy(user);
        assertThat(i).isEqualTo(5);
    }

    @Test
    void findByVisibilityAndUserId() {
        Role role = new Role(1, "admin");
        User user = new User(1, "test", "test1", role);

        List<Post> post = postRepository.findAllByVisibilityAndCreatedBy(true, user);

        assertThat(post.size()).isEqualTo(4);
    }
}