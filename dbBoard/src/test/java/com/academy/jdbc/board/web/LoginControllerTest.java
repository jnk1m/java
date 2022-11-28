package com.academy.jdbc.board.web;

import com.academy.jdbc.board.domain.User;
import com.academy.jdbc.board.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LoginControllerTest {
    private MockMvc mockMvc;
    UserService userService;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new LoginController(userService))
                .alwaysDo(print())
                .build();
    }

    @Test
    void doLogin() throws Exception {
        User user = new User(1, "test", "test1", 1);
        when(userService.login("test", "test1")).thenReturn(Optional.of(user));

        mockMvc.perform(post("/login"))
                .andExpect(status().isOk());
    }

    @Test
    void doLogout() {
    }
}