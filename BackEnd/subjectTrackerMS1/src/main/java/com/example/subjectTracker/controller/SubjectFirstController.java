package com.example.subjectTracker.controller;

import com.example.subjectTracker.entity.SubjectTracker;
import com.example.subjectTracker.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/")
public class SubjectFirstController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("print")
    public String print(){
        return "Hello World :) Vivek";
    }

    @GetMapping("subjects")
    public List<SubjectTracker> getAllSubjects(){
        return subjectService.getAllSubjects();
    }

    @GetMapping("subject/{id}")
    public ResponseEntity<SubjectTracker> getSubjectById(@PathVariable Integer id){
        var subject = subjectService.getSubjectWithId(id);
        return ResponseEntity.ok(subject);
    }

    @PostMapping("subject")
    public ResponseEntity<Object> saveSubjectData(@RequestBody SubjectTracker subjectTracker){
        var subjectTrackerResult  = subjectService.saveSubjectData(subjectTracker);
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectTrackerResult);
    }

    @DeleteMapping("subject/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable Integer id){
        var result = subjectService.deleteSubject(id);
        return ResponseEntity.ok(result);
    }

}
