package com.example.subjectTracker.controller;

import com.example.subjectTracker.entity.Topic;
import com.example.subjectTracker.service.TopicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TopicFirstControllerTest {

    @InjectMocks
    private TopicFirstController topicFirstController;

    @Mock
    private TopicService topicService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(topicFirstController).build();
    }

    @Test
    public void testSaveTopic() throws Exception {
        Topic topic = new Topic();
        topic.setTopicId(1);
        topic.getTopicName();

        when(topicService.saveTopic(any(Topic.class), anyInt())).thenReturn(topic);

        mockMvc.perform(post("/Topic/saveTopic/1")
                        .contentType("application/json")
                        .content("{\"name\":\"Sample Topic\"}"))
                .andExpect(status().isOk());

        ResponseEntity<Topic> responseEntity = topicFirstController.saveTopic(topic, 1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(topic, responseEntity.getBody());
    }

    @Test
    public void testGetAllTopicsBysubjectId() throws Exception {
        Topic topic1 = new Topic();
        topic1.setTopicId(1);
        topic1.setTopicName("Topic 1");

        Topic topic2 = new Topic();
        topic2.setTopicId(2);
        topic2.setTopicName("Topic 2");

        List<Topic> topics = Arrays.asList(topic1, topic2);
        when(topicService.getAllTopicsBysubjectId(anyInt())).thenReturn(topics);

        mockMvc.perform(get("/Topic/getTopicsBYsubjectId/1"))
                .andExpect(status().isOk());

        ResponseEntity<List<Topic>> responseEntity = topicFirstController.getAllTopicsBysubjectId(1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(topics, responseEntity.getBody());
    }
}
