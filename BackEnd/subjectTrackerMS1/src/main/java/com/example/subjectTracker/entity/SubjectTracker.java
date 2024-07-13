package com.example.subjectTracker.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
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
public class SubjectTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subjectId;

    @JsonProperty("Subject")
    @Column(name = "subject")
    private String subjectName;

//    @JsonProperty("Topic")
//    @Column(name = "topic")
//    private String topicName;

//    @OneToMany(mappedBy = "subjectTracker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Topic> Topics;

    @CreationTimestamp
    @Column(name = "timestamp")
    private Timestamp createdTimeStamp;
}
