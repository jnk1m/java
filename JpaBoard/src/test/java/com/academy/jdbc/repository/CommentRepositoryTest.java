package com.academy.jdbc.repository;

import com.academy.jdbc.config.RootConfig;
import com.academy.jdbc.config.WebConfig;
import com.academy.jdbc.entity.Comment;
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

import java.time.LocalDateTime;
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
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void findByIdTest() {
        Optional<Comment> comment = commentRepository.findById(4);
        assertThat(comment.get().getCommentId()).isEqualTo(4);
    }

    @Test
    void findAllTest() {
        List<Comment> all = commentRepository.findAll();
        assertThat(all.size()).isEqualTo(19);
    }

    @Test
    void countByCreatedByTest() {
        Role role = new Role(1, "admin");
        User user = new User(1, "test", "test1", role);
        int commentCnt = commentRepository.countByCreatedBy(user);
        assertThat(commentCnt).isEqualTo(1);
    }

    @Test
    void update_success() {
        int updateComment = commentRepository.updateComment("test", 4);
        Optional<Comment> comment = commentRepository.findById(4);
        assertThat(updateComment).isEqualTo(1);
        assertThat(comment.get().getContent()).isEqualTo("test");
    }

    @Test
    void queryDslTest() {

        List<Comment> commentsAfterWriteDate = commentRepository.getWriteDate(
                LocalDateTime.of(2022, 11, 27, 9, 0,0));

        assertThat(commentsAfterWriteDate).hasSize(11);
    }
}