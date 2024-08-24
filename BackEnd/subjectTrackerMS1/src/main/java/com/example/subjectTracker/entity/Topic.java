package com.example.subjectTracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

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

    @Column(name = "topicName")
    private String topicName;

    @Column(name = "inputOverview")
    private String inputOverview;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectId", nullable = false)
    @JsonBackReference // Prevent infinite recursion from the topic side
    private SubjectTracker subjectTracker;

    @CreationTimestamp
    @Column(name = "timestamp")
    private Timestamp createdTimeStamp;

}
