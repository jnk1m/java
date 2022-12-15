package com.academy.board.bootboard.controller;

import com.academy.board.bootboard.entity.Post;
import com.academy.board.bootboard.entity.User;
import com.academy.board.bootboard.repository.PostRepository;
import com.academy.board.bootboard.service.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class PostRepositoryTest {

//CRUD 중에 Read만 테스트하면 됨 + 거기에 내 레포지토리에 있는 메서드들 테스트하기..

    @InjectMocks
    PostServiceImpl postService;

    @Mock
    PostRepository postRepository;

    User user;
    Post post;

    @BeforeEach
    void setUp() {
        user = new User("test11", "test1235");
        post = new Post("test","testContent",user,true);
    }

    @Test
    void getPostsList() throws Exception {
        //given
        given(postRepository.findAll())
                .willReturn(List.of(post));

        //when
        List<Post> allPosts = postService.getAllPosts();

        //then
        BDDMockito.then(postRepository).should().findAll();
        assertThat(allPosts.get(0).getTitle()).isEqualTo("test");
    }

    @Test
    void getPost() {
        Integer id = 0;

        //given
        given(postRepository.findById(id)).willReturn(Optional.ofNullable(post));

        //when
        Post post1 = postService.getPost(id);

        //then
        assertThat(post1.getTitle()).isEqualTo("test"); //값 검증

        verify(postRepository,times(1)).findById(anyInt()); //행위 검증
    }


    @Test
    void testFindAllByVisibility(){
        given(postRepository.findAllByVisibility(true)).willReturn(List.of(post));

        List<Post> visiblePosts = postService.getVisiblePosts(true);

        BDDMockito.then(postRepository).should().findAllByVisibility(true);
        assertThat(visiblePosts.get(0).getTitle()).isEqualTo("test");
    }

}