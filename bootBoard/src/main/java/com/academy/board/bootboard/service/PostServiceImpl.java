package com.academy.board.bootboard.service;

import com.academy.board.bootboard.entity.Post;
import com.academy.board.bootboard.entity.User;
import com.academy.board.bootboard.exception.PostNotFoundException;
import com.academy.board.bootboard.repository.PostRepository;
import com.academy.board.bootboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getVisiblePosts(boolean isTrue) {
        return postRepository.findAllByVisibility(isTrue);
    }


    @Override
    @Transactional(readOnly = true)
    public Post getPost(int postId) throws PostNotFoundException {
        return postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    }

    @Override
    @Transactional
    public void writePost(String title, String content, int userId) {
        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        postRepository.save(new Post(title, content, user));
    }
}
