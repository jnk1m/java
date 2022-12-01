package com.academy.jdbc.repository;

import com.academy.jdbc.config.RootConfig;
import com.academy.jdbc.config.WebConfig;
import com.academy.jdbc.entity.Like;
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
class LikeRepositoryTest {
    @Autowired
    LikeRepository likeRepository;

    @Test
    void test() {
        Like.Pk pk = new Like.Pk(3, 2);
        Optional<Like> byId = likeRepository.findById(pk);
        assertThat(byId.get().getPk()).isEqualTo(pk);
    }

    @Test
    void getAllLikedTest() {
        List<Like> allByLiked = likeRepository.getAllByLiked(true);
        assertThat(allByLiked.size()).isEqualTo(2);
    }
}