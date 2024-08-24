package com.example.subjectTracker.service;

import com.example.subjectTracker.entity.SubjectTracker;
import com.example.subjectTracker.entity.Topic;

import java.util.List;

public interface SubjectService {

    List<SubjectTracker> getAllSubjects();

    SubjectTracker saveSubjectData(SubjectTracker subjectTracker);

    SubjectTracker getSubjectWithId(Integer id);

    String deleteSubject(Integer Id);

    SubjectTracker getSubjectByName(String subjectName);

    List<Topic> getAllTopicsBySubjectId(Integer SubjectId);

}
