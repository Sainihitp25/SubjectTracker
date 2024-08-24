package com.example.subjectTracker.service.impl;

import com.example.subjectTracker.entity.SubjectTracker;
import com.example.subjectTracker.repository.SubjectRepository;
import com.example.subjectTracker.service.SubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SubjectServiceImplTest {

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private SubjectService subjectService;

    private SubjectTracker subjectTracker;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subjectTracker = new SubjectTracker();
        subjectTracker.setSubjectName("Test Subject");
        subjectTracker.setSubjectId(1);
    }

    @Test
    void testGetAllSubjects() {
        when(subjectRepository.findAll()).thenReturn(Arrays.asList(subjectTracker));

        List<SubjectTracker> subjects = subjectService.getAllSubjects();
        assertNotNull(subjects);
        assertEquals(1, subjects.size());
        assertEquals("Test Subject", subjects.get(0).getSubjectName());
    }

    @Test
    void testSaveSubjectData() {
        when(subjectRepository.save(any(SubjectTracker.class))).thenReturn(subjectTracker);

        SubjectTracker savedSubject = subjectService.saveSubjectData(subjectTracker);
        assertNotNull(savedSubject);
        assertEquals("Test Subject", savedSubject.getSubjectName());
    }

    @Test
    void testGetSubjectWithId() {
        when(subjectRepository.findById(1)).thenReturn(Optional.of(subjectTracker));

        SubjectTracker foundSubject = subjectService.getSubjectWithId(1);
        assertNotNull(foundSubject);
        assertEquals("Test Subject", foundSubject.getSubjectName());
    }

    @Test
    void testGetSubjectWithIdNotFound() {
        when(subjectRepository.findById(1)).thenReturn(Optional.empty());

        SubjectTracker foundSubject = subjectService.getSubjectWithId(1);
        assertNull(foundSubject);
    }

    @Test
    void testDeleteSubject() {
        when(subjectRepository.existsById(1)).thenReturn(true);

        String response = subjectService.deleteSubject(1);
        assertEquals("Subject deleted successfully.", response);
        verify(subjectRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteSubjectNotFound() {
        when(subjectRepository.existsById(1)).thenReturn(false);

        String response = subjectService.deleteSubject(1);
        assertEquals("Subject not found.", response);
        verify(subjectRepository, never()).deleteById(anyInt());
    }
}
