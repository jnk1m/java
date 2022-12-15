package com.academy.finalproject.controller;

import com.academy.finalproject.entity.Department;
import com.academy.finalproject.entity.DepartmentMember;
import com.academy.finalproject.entity.Employee;
import com.academy.finalproject.service.DeptMemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DeptMemberController.class)
class DeptMemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DeptMemberService deptMemberService;

    @MockBean
    ModelMapper modelMapper;

    DepartmentMember departmentMember;


    @BeforeEach
    void setUp() {
        Department department = new Department("L1004", "백엔드4팀");
        Employee employee = new Employee(20221110L, "한이름");
        DepartmentMember.Pk pk = new DepartmentMember.Pk(department.getDepartmentId(), employee.getEmployeeId());
        departmentMember = new DepartmentMember(pk, department, employee);

    }

    @Test
    @DisplayName("조건에 맞춰 요청하면 200 응답하는지 확인")
    void testGetDeptAndMember() throws Exception {
        String id = "L1004";
        given(deptMemberService.getDeptAndMember(id)).willReturn(List.of(departmentMember, departmentMember));

        mockMvc.perform(get("/department-members")
                        .param("departmentId", id)
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    @DisplayName("쿼리 파라미터 departmentId 없이 요청하면 400 응답하는지 확인")
    void testWithOutDepartmentId() throws Exception {
        String id = "L1004";
        given(deptMemberService.getDeptAndMember(id)).willReturn(List.of(departmentMember, departmentMember));

        mockMvc.perform(get("/department-members")
                        .accept("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Access Header 없이 요청하면 400 응답하는지 확인")
    void testWithOutAccessHeader() throws Exception {
        String id = "L1004";
        given(deptMemberService.getDeptAndMember(id)).willReturn(List.of(departmentMember, departmentMember));

        mockMvc.perform(get("/department-members")
                        .param("departmentId", id))
                .andExpect(status().isBadRequest());
    }


}