package com.example.subjectTracker.controller;

import com.example.subjectTracker.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/")
public class SubjectSecondController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("ok")
    public void getSubjectNotifications(){
        notificationService.scheduleNotifications();
    }
}
