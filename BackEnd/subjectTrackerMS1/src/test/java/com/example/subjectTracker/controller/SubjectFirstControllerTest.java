package com.example.subjectTracker.controller;

import com.example.subjectTracker.entity.SubjectTracker;
import com.example.subjectTracker.service.SubjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SubjectFirstController.class)
public class SubjectFirstControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubjectService subjectService;

    @Test
    public void testPrint() throws Exception {
        mockMvc.perform(get("/print"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World :) Vivek"));
    }

    @Test
    public void testGetAllSubjects() throws Exception {
        SubjectTracker subject1 = new SubjectTracker(); // add appropriate constructor or setters
        SubjectTracker subject2 = new SubjectTracker(); // add appropriate constructor or setters
        List<SubjectTracker> subjects = Arrays.asList(subject1, subject2);

        when(subjectService.getAllSubjects()).thenReturn(subjects);

        mockMvc.perform(get("/subjects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(subjects.size()));
    }

    @Test
    public void testGetSubjectById() throws Exception {
        SubjectTracker subject = new SubjectTracker(); // add appropriate constructor or setters
        when(subjectService.getSubjectWithId(anyInt())).thenReturn(subject);

        mockMvc.perform(get("/subject/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subjectId").value(subject.getSubjectId())); // assuming getId() is a method in SubjectTracker
    }


    @Test
    public void testSaveSubjectData() throws Exception {
        SubjectTracker subject = new SubjectTracker();
        when(subjectService.saveSubjectData(createSubjectTracker(subject))).thenReturn(subject);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(subject);

        mockMvc.perform(MockMvcRequestBuilders.post("/subject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    private SubjectTracker createSubjectTracker(SubjectTracker subject) {
        subject.setSubjectName("Maths");
        return subject;
    }


    @Test
    public void testDeleteSubject() throws Exception {
        when(subjectService.deleteSubject(anyInt())).thenReturn("Deleted");

        mockMvc.perform(delete("/subject/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));
    }


}
