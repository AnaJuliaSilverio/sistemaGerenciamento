package com.example.sistemaGerenciamento.controllers;

import com.example.sistemaGerenciamento.dtos.TeacherRequestDTO;
import com.example.sistemaGerenciamento.dtos.TeacherResponseDTO;
import com.example.sistemaGerenciamento.services.TeacherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeacherContoller.class)
public class TeacherControllerTest {

    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private TeacherService teacherService;
    @Autowired
    private TeacherContoller teacherContoller;
    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(teacherContoller)
                .alwaysDo(print())
                .build();
    }
    @Test
    void shouldReturnCreatedStatusWhenITryCreateATeacher() throws Exception{
        TeacherRequestDTO teacherRequestDTO = new TeacherRequestDTO("Alessandra");
        when(teacherService.createTeacher(teacherRequestDTO)).thenReturn(new TeacherResponseDTO());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/teacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacherRequestDTO)))
                .andExpect(status().isCreated());;
    }

    @Test
    void shouldReturnBadRequestStatusWhenITryCreateAStudentWithWrongBody() throws Exception{
        TeacherRequestDTO teacherRequestDTO = new TeacherRequestDTO();
        when(teacherService.createTeacher(teacherRequestDTO)).thenReturn(new TeacherResponseDTO());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/teacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacherRequestDTO)))
                .andExpect(status().isBadRequest());;
    }

    @Test
    void shouldReturnOkStatusWhenITryToGetAllTeacher() throws Exception{
        when(teacherService.getAllTeachers()).thenReturn(Collections.singletonList(new TeacherResponseDTO()));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/teacher")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void shouldReturnOkStatusWhenITryGetCoursesByTeacherName() throws Exception{
        when(teacherService.findyCoursesByTeacherName("Paula")).thenReturn(new ArrayList<String>());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/teacher/Paula")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
