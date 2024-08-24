package com.example.subjectTracker.controller;

import com.example.subjectTracker.dto.SubjectTrackerDTO;
import com.example.subjectTracker.dto.TopicDTO;
import com.example.subjectTracker.entity.SubjectAndTopicRequest;
import com.example.subjectTracker.entity.SubjectTracker;
import com.example.subjectTracker.entity.Topic;
import com.example.subjectTracker.service.SubjectService;
import com.example.subjectTracker.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
@RequestMapping("/")
public class SubjectController {

    //TODO need to write appropriate loggers
    private static final Logger logger = LoggerFactory.getLogger(SubjectController.class);

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TopicService topicService;

    @GetMapping("subjects")
    public ResponseEntity<List<SubjectTrackerDTO>> getAllSubjects() {
        List<SubjectTracker> subjects = subjectService.getAllSubjects();

        List<SubjectTrackerDTO> subjectDTOs = subjects.stream().map(subject -> {
            SubjectTrackerDTO dto = new SubjectTrackerDTO();
            dto.setSubjectId(subject.getSubjectId());
            dto.setSubjectName(subject.getSubjectName());
            dto.setTopics(subject.getTopics().stream().map(topic -> {
                TopicDTO topicDTO = new TopicDTO();
                topicDTO.setTopicId(topic.getTopicId());
                topicDTO.setTopicName(topic.getTopicName());
                return topicDTO;
            }).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(subjectDTOs);
    }

    @GetMapping("subjectName/{subjectName}")
    public ResponseEntity<SubjectTracker> getTopicsForSubject(@PathVariable(required = true) String subjectName) {
        if (subjectName == null || subjectName.isEmpty()) {
            throw new IllegalArgumentException("Subject name must not be null or empty");
        }
        return ResponseEntity.ok(subjectService.getSubjectByName(subjectName));
    }

    @PostMapping("addSubjectAndTopic")
    public ResponseEntity<SubjectTrackerDTO> createOrUpdateSubjectAndTopic(@RequestBody SubjectAndTopicRequest request) {
        // Check if the Subject exists
        SubjectTracker subject = subjectService.getSubjectByName(request.getSubjectName());

        if (subject == null) {
            subject = new SubjectTracker();
            subject.setSubjectName(request.getSubjectName());
            subject = subjectService.saveSubjectData(subject);
        }

        // Create and save the topic
        Topic topic = new Topic();
        topic.setTopicName(request.getTopicName());
        topic.setInputOverview("");
        topic.setCreatedTimeStamp(Timestamp.valueOf(LocalDateTime.now()));
        topic.setSubjectTracker(subject);

        Topic savedTopic = topicService.saveTopic(topic);

        // Add the new topic to the subject's list of topics
        if (subject.getTopics() == null) {
            subject.setTopics(new ArrayList<>());
        }
        subject.getTopics().add(savedTopic);
        subjectService.saveSubjectData(subject);

        // Map entities to DTOs
        SubjectTrackerDTO subjectDTO = new SubjectTrackerDTO();
        subjectDTO.setSubjectId(subject.getSubjectId());
        subjectDTO.setSubjectName(subject.getSubjectName());
        subjectDTO.setTopics(subject.getTopics().stream().map(t -> {
            TopicDTO topicDTO = new TopicDTO();
            topicDTO.setTopicId(t.getTopicId());
            topicDTO.setTopicName(t.getTopicName());
            topicDTO.setInputOverview(t.getInputOverview());
            return topicDTO;
        }).collect(Collectors.toList()));

        return ResponseEntity.ok(subjectDTO);
    }
}
