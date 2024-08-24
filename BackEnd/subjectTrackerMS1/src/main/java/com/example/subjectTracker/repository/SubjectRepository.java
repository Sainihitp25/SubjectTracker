package com.example.subjectTracker.repository;

import com.example.subjectTracker.entity.SubjectTracker;
import com.example.subjectTracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectTracker, Integer> {

    SubjectTracker findBySubjectNameIgnoreCase(String subjectName);

    List<SubjectTracker> findByUser(User user);
}
