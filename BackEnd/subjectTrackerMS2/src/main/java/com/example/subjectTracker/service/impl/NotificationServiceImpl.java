package com.example.subjectTracker.service.impl;

import com.example.subjectTracker.entity.SubjectTracker;
import com.example.subjectTracker.repository.SubjectTrackerRepository;
import com.example.subjectTracker.service.NotificationService;
import com.example.subjectTracker.service.WhatsAppSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private SubjectTrackerRepository subjectRepository;

    @Autowired
    private WhatsAppSender whatsAppSender;

    @Autowired
    private ConfigurableApplicationContext context;

    public void scheduleNotifications() {

        List<SubjectTracker> subjects = subjectRepository.findAll();

        for (SubjectTracker subject : subjects) {

            LocalDateTime seventhDay = subject.getCreatedTimeStamp().toLocalDateTime().plusDays(7);
            LocalDateTime fourteenthDay = subject.getCreatedTimeStamp().toLocalDateTime().plusDays(14);

            String message = "Reminder for subject: " + subject.getSubjectName() + " - Topic: " + subject.getTopicName();

            if (LocalDateTime.now().isEqual(seventhDay) || LocalDateTime.now().isEqual(fourteenthDay)) {
                whatsAppSender.sendWhatsAppMessage("+919638425338", message);  // this for every 7th and 14th day
            }
            whatsAppSender.sendWhatsAppMessage("+919638425338", message);  // This method is just checking for validations
        }

        context.close();   // This closes the application after running once a day

    }
}
