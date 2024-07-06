package com.example.subjectTracker.service;

import com.example.subjectTracker.entity.SubjectTracker;

import java.util.List;

public interface SubjectService {

    List<SubjectTracker> getAllSubjects();

    SubjectTracker saveSubjectData(SubjectTracker subjectTracker);

    SubjectTracker getSubjectWithId(Integer id);

    String deleteSubject(Integer Id);

}
