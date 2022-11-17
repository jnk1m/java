package com.academy.score.controller;

import com.academy.score.domain.Student;
import com.academy.score.exception.StudentNotExistException;
import com.academy.score.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
class RestApiControllerTest {
    private MockMvc mockMvc;
    StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = mock(StudentRepository.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new RestApiController(studentRepository))
                .defaultRequest(get("/students/1").accept(MediaType.APPLICATION_JSON))
                .alwaysDo(print())
                .build();
    }

    @Test
    void getStudentTest() throws Exception {
        Student student = new Student(1, "Kurt", "kurt@cobain.com", 100, "Nirvana");
        when(studentRepository.getStudent(anyLong())).thenReturn(student);
        when(studentRepository.exists(anyLong())).thenReturn(true);

        mockMvc.perform(get("/students/{studentId}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.email").value(student.getEmail()))
                .andExpect(jsonPath("$.score").value(student.getScore()))
                .andExpect(jsonPath("$.comment").value(student.getComment()));
    }

    @Test
    void studentNotExistTest() {
        Throwable throwable = catchThrowable(() ->
                mockMvc.perform(get("/students/{studentId}", "2"))
                        .andExpect(status().isNotFound()));

        assertThat(throwable).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(StudentNotExistException.class);
    }

    @Test
    void doStudentRegister() throws Exception {
//        Student student = new Student(1, "Kurt", "kurt@cobain.com", 100, "Nirvana");
//        when(studentRepository.register("Kurt", "kurt@cobain.com", 100, "Nirvana")).thenReturn(student);
//
//        MvcResult mvcResult = mockMvc.perform(post("/students")
//                .contentType(noCon).content(inputJson)).andReturn();
//        //MvcResult mvcResult = mockMvc.perform(post("/students").con)
//
//        mockMvc.perform(post("/students")).andExpect(status().isCreated());


//header에 location을 가져와서 거기로 get 요청을 보내서 가져오는지

    }

    @Test
    void doStudentRegister_fail() {
    }

    @Test
    void doPutStudent() {
    }
}