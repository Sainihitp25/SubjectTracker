package com.example.subjectTracker.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class TwilioConfig {

    @Getter
    @Value("${twilio.account.sid}")
    public static String twilioAccountId;

    @Getter
    @Value("${twilio.account.token}")
    public static String twilioAccountAuthToken;

}
