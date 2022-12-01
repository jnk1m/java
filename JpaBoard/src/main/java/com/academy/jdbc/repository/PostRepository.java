package com.academy.jdbc.repository;

import com.academy.jdbc.entity.Post;
import com.academy.jdbc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer>, PostRepositoryCustom {
    int countByCreatedBy(User createdBy);

    @Query("select p from Post p where p.visibility = ?1 and p.createdBy = ?2")
    List<Post> findAllByVisibilityAndCreatedBy(boolean visibility, User createdBy);


}
