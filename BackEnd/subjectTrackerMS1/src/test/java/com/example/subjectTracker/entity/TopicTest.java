package com.example.subjectTracker.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TopicTest {

    private Topic topic;

    @Mock
    private SubjectTracker subjectTracker;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        topic = Topic.builder()
                .topicId(1)
                .topicName("Test Topic")
                .inputOverview("This is a test overview")
                .subjectTracker(subjectTracker)
                .build();
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(1, topic.getTopicId());
        assertEquals("Test Topic", topic.getTopicName());
        assertEquals("This is a test overview", topic.getInputOverview());
        assertEquals(subjectTracker, topic.getSubjectTracker());

        topic.setTopicId(2);
        topic.setTopicName("Updated Topic");
        topic.setInputOverview("Updated overview");
        topic.setSubjectTracker(null);

        assertEquals(2, topic.getTopicId());
        assertEquals("Updated Topic", topic.getTopicName());
        assertEquals("Updated overview", topic.getInputOverview());
        assertNull(topic.getSubjectTracker());
    }

    @Test
    void testToString() {
        String expected = "Topic(topicId=1, topicName=Test Topic, inputOverview=This is a test overview, subjectTracker=" + subjectTracker + ")";
        assertEquals(expected, topic.toString());
    }

    @Test
    void testNoArgsConstructor() {
        Topic emptyTopic = new Topic();
        assertNull(emptyTopic.getTopicId());
        assertNull(emptyTopic.getTopicName());
        assertNull(emptyTopic.getInputOverview());
        assertNull(emptyTopic.getSubjectTracker());
    }

    @Test
    void testAllArgsConstructor() {
        Topic newTopic = new Topic(3, "New Topic", "New overview", subjectTracker);
        assertEquals(3, newTopic.getTopicId());
        assertEquals("New Topic", newTopic.getTopicName());
        assertEquals("New overview", newTopic.getInputOverview());
        assertEquals(subjectTracker, newTopic.getSubjectTracker());
    }
}
