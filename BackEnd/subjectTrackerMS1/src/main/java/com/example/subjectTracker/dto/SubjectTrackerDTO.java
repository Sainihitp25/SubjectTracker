package com.example.subjectTracker.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubjectTrackerDTO {

    private Integer subjectId;
    private String subjectName;
    private List<TopicDTO> topics; // Nested DTO for topics

    // Getters and setters
}
