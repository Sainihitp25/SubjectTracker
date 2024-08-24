package com.example.subjectTracker.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Data
public class SubjectAndTopicRequest {

    @JsonProperty("subject")
    private String subjectName;

    @JsonProperty("topic")
    private String topicName;
}
