package com.example.subjectTracker.repository;

import com.example.subjectTracker.entity.SubjectTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectTracker, Integer> {

}
