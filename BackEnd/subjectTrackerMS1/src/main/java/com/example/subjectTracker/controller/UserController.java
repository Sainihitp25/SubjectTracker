package com.example.subjectTracker.controller;

import com.example.subjectTracker.entity.SubjectTracker;
import com.example.subjectTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}/subjects")
    public ResponseEntity<List<SubjectTracker>> getSubjectsForUser(@PathVariable String username) {
        List<SubjectTracker> subjects = userService.getSubjectsForUser(username);
        return ResponseEntity.ok(subjects);
    }

    @PostMapping("/{username}/subject")
    public ResponseEntity<SubjectTracker> saveSubjectForUser(@PathVariable String username, @RequestBody SubjectTracker subjectTracker) {
        SubjectTracker savedSubject = userService.saveSubjectForUser(username, subjectTracker);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSubject);
    }
}
