package com.academy.board.bootboard.controller;

import com.academy.board.bootboard.config.SecurityConfig;
import com.academy.board.bootboard.entity.Post;
import com.academy.board.bootboard.entity.User;
import com.academy.board.bootboard.repository.PostRepository;
import com.academy.board.bootboard.service.PostServiceImpl;
import com.academy.board.bootboard.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PostController.class)
@AutoConfigureMockMvc(addFilters = false)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostServiceImpl postService;

    @MockBean
    UserService userService;

    @MockBean
    AuthenticationProvider authenticationProvider;

    User user;
    Post post;

    @BeforeEach
    void setUp() {
        user = new User("test11", "test1235");
        post = new Post("test","testContent",user,true);
    }

    @Test
    void getPostsList() throws Exception {
        given(postService.getVisiblePosts(true)).willReturn(List.of(post));

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk());
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].title", equalTo("test")));

    }

    @Test
    void getPost() {
    }

    @Test
    void getWriteForm() {
    }

    @Test
    void doWritePost() {
    }
}