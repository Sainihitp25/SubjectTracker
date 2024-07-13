package com.example.subjectTracker.repository;

import com.example.subjectTracker.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Integer> {

//    @Query("select t from Topic t where t.subjectId = :subjectId")
//    List<Topic> findBysubjectId(@Param("subjectId") Integer subjectId);

}
