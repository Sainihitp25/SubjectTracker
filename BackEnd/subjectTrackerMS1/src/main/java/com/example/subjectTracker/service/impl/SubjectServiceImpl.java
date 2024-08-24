package com.example.subjectTracker.service.impl;

import com.example.subjectTracker.entity.SubjectTracker;
import com.example.subjectTracker.entity.Topic;
import com.example.subjectTracker.exception.ResourceNotFoundException;
import com.example.subjectTracker.repository.SubjectRepository;
import com.example.subjectTracker.repository.TopicRepository;
import com.example.subjectTracker.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public SubjectTracker getSubjectByName(String subjectName) {
        return subjectRepository.findBySubjectNameIgnoreCase(subjectName);
    }

    @Override
    public List<SubjectTracker> getAllSubjects() {
        return subjectRepository.findAll();
    }


    @Override
    public SubjectTracker saveSubjectData(SubjectTracker subjectTracker) {
       return subjectRepository.save(subjectTracker);
    }

    @Override
    public SubjectTracker getSubjectWithId(Integer id) {
        return subjectRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("nothing found for id"));
    }

    @Override
    public String deleteSubject(Integer id) {
        if(getSubjectWithId(id)!=null) {
            subjectRepository.deleteById(id);
            return "Deleted successfully";
        }
        return "Nothing found for given id";
    }

    public List<Topic> getAllTopicsBySubjectId(Integer subjectId) {
        return topicRepository.findBySubjectId(subjectId);
    }

}
