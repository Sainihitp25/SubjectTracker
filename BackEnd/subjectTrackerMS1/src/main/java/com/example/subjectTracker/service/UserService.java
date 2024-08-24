package com.example.subjectTracker.service;

import com.example.subjectTracker.entity.SubjectTracker;
import com.example.subjectTracker.entity.User;
import com.example.subjectTracker.exception.ResourceNotFoundException;
import com.example.subjectTracker.repository.SubjectRepository;
import com.example.subjectTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectTrackerRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public List<SubjectTracker> getSubjectsForUser(String username) {
        User user = getUserByUsername(username);
        return subjectTrackerRepository.findByUser(user);
    }

    public SubjectTracker saveSubjectForUser(String username, SubjectTracker subjectTracker) {
        User user = getUserByUsername(username);
        subjectTracker.setUser(user);
        return subjectTrackerRepository.save(subjectTracker);
    }
}

