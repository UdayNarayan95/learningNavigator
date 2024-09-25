package com.uday.learningNavigator.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;




import com.uday.learningNavigator.dtos.ExamDto;
import com.uday.learningNavigator.exchanges.CreateExamRequest;
import com.uday.learningNavigator.services.IExamService;

public class ExamControllerTest {
    private MockMvc mockMvc;

    @Mock
    private IExamService examService;

    @InjectMocks
    private ExamController examController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(examController).build();
    }

    @Test
    void testCreateExam() throws Exception {
        CreateExamRequest request = new CreateExamRequest();
        request.setSubjectId(1L);
        ExamDto examDto = new ExamDto();
        
        when(examService.createExam(any(CreateExamRequest.class))).thenReturn(examDto);

        ResponseEntity<ExamDto> response = examController.createExam(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(examService, times(1)).createExam(any(CreateExamRequest.class));
    }

    // @Test
    // void testGetExamById() throws Exception {
    //     long examId = 1L;
    //     ExamDto examDto = new ExamDto();

    //     when(examService.findExamById(examId)).thenReturn(examDto);

    //     mockMvc.perform(get("/exams/{examId}", examId))
    //            .andExpect(status().isOk())
    //            .andExpect(jsonPath("$.id").exists());
        
    //     verify(examService, times(1)).findExamById(examId);
    // }
    @Test
void testGetExamById() throws Exception {
    long examId = 1L;
    ExamDto examDto = new ExamDto();
    examDto.setId(examId);

    when(examService.findExamById(examId)).thenReturn(examDto);

    mockMvc.perform(get("/exams/{examId}", examId))
           .andExpect(status().isOk())                      // Verifying status is OK
           .andExpect(jsonPath("$.id").value(examId));       // Verifying JSON structure
}


    @Test
    void testGetAllExams() throws Exception {
        List<ExamDto> exams = Arrays.asList(new ExamDto(), new ExamDto());
        when(examService.findAllExams()).thenReturn(exams);

        mockMvc.perform(get("/exams"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.exams").isArray());

        verify(examService, times(1)).findAllExams();
    }

    @Test
    void testDeleteExam() throws Exception {
        long examId = 1L;

        mockMvc.perform(delete("/exams/{examId}", examId))
               .andExpect(status().isOk());

        verify(examService, times(1)).deleteExam(examId);
    }
}
