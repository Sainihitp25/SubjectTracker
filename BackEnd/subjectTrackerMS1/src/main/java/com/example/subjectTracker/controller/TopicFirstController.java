package com.example.subjectTracker.controller;

import com.example.subjectTracker.entity.Topic;
import com.example.subjectTracker.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Topic")
public class TopicFirstController {

    @Autowired
    private TopicService topicService;

    @PostMapping("/saveTopic/{subjectId}")
    public ResponseEntity<Topic> saveTopic(@RequestBody Topic topic, @PathVariable Integer subjectId)
    {
        var TopicResult = topicService.saveTopic(topic,subjectId);
        return ResponseEntity.ok(TopicResult);
    }

    @GetMapping("/getTopicsBYsubjectId/{subjectId}")
    public ResponseEntity<List<Topic>> getAllTopicsBysubjectId(@PathVariable Integer subjectId)
    {
        var result = topicService.getAllTopicsBysubjectId(subjectId);
        return ResponseEntity.ok(result);
    }
}
