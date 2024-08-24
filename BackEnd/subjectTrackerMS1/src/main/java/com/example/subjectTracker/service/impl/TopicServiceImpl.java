package com.example.subjectTracker.service.impl;

import com.example.subjectTracker.entity.Topic;
import com.example.subjectTracker.exception.ResourceNotFoundException;
import com.example.subjectTracker.repository.SubjectRepository;
import com.example.subjectTracker.repository.TopicRepository;
import com.example.subjectTracker.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private SubjectRepository  subjectRepository;


    @Override
    public Topic saveTopic(Topic topic) {
            return topicRepository.save(topic);

    }

    public List<Topic> getAllTopicsBysubjectId(Integer subjectId) {

        if (subjectRepository.existsById(subjectId))
        {
//            return topicRepository.findBysubjectId(subjectId);
            return topicRepository.findAll();
        }
        else
        {
            throw new ResourceNotFoundException("nothing found for id");
        }

    }

    public Topic getTopicById(Integer topicId) {
        return topicRepository.findById(topicId).orElse(null);
    }

}
