package com.example.subjectTracker.service;

import com.example.subjectTracker.entity.Topic;

import java.util.List;

public interface TopicService {
    Topic saveTopic(Topic topic , Integer subjectId);
    List<Topic> getAllTopicsBysubjectId(Integer subjectId);
    Topic getTopicById(Integer topicId);
}
