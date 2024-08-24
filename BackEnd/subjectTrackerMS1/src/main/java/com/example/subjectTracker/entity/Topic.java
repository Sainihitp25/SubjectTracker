package com.example.subjectTracker.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer topicId;

    @JsonProperty("TopicName")
    @Column(name = "topicName")
    private String topicName;

    @JsonProperty("InputOverview")
    @Column(name = "inputOverview")
    private String inputOverview;

    @JoinColumn(name = "subjectId")
    @ManyToOne(cascade = CascadeType.ALL)
    private SubjectTracker subjectTracker;

}
