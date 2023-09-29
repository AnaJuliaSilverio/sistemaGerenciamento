package com.example.sistemaGerenciamento.controllers;

import com.example.sistemaGerenciamento.dtos.RegistratioRequestDTO;
import com.example.sistemaGerenciamento.dtos.RegistrationResponseDTO;
import com.example.sistemaGerenciamento.services.RegistrationService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {

    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private RegistrationService registrationService;

    @Autowired
    private RegistrationController registrationController;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController)
                .alwaysDo(print())
                .build();
    }

    @Test
    void shouldReturnCreatedStatusWhenITryCreateARegistration() throws Exception{
        RegistratioRequestDTO registratioRequestDTO = new RegistratioRequestDTO(LocalDate.parse("2020-10-15"));

        when(registrationService.createRegistration(registratioRequestDTO,1L,1L)).thenReturn(new RegistrationResponseDTO());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/registration/1/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registratioRequestDTO)))
                .andExpect(status().isCreated());;
    }

    @Test
    void shouldReturnCreatedStatusWhenITryCreateAStudentWithWrongBody() throws Exception{
        RegistratioRequestDTO registratioRequestDTO = new RegistratioRequestDTO();

        when(registrationService.createRegistration(registratioRequestDTO,1L,1L)).thenReturn(new RegistrationResponseDTO());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/registration/1/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registratioRequestDTO)))
                .andExpect(status().isBadRequest());;
    }
    @Test
    void shouldReturnOkStatusWhenITryToGetAllRegistration() throws Exception{
        when(registrationService.getAllRegistration()).thenReturn(Collections.singletonList(new RegistrationResponseDTO()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/registration")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());;
    }

    @Test
    void shouldReturnOkStatusWhenITryToGetStudentsEnrolledInMultipleCourses() throws Exception{
        when(registrationService.findStudentNamesByCourseName("geografia")).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/registration/students/geografia")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());;
    }
}
