package com.academy.board.bootboard.repository;

import com.academy.board.bootboard.entity.Post;
import com.academy.board.bootboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PostRepository extends JpaRepository<Post, Integer> {
    int countByCreatedBy(User createdBy);

    @Query("select p from Post p where p.visibility = ?1 and p.createdBy = ?2")
    List<Post> findAllByVisibilityAndCreatedBy(boolean visibility, User createdBy);

    List<Post> findAllByVisibility(boolean isTrue);


}
