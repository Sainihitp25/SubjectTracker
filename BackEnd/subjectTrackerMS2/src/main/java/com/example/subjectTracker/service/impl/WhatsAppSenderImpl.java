package com.example.subjectTracker.service.impl;

import com.example.subjectTracker.config.TwilioConfig;
import com.example.subjectTracker.service.WhatsAppSender;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppSenderImpl implements WhatsAppSender {

    @Autowired
    private TwilioConfig twilioConfig;

    private static final String TWILIO_SANDBOX_NUMBER = "whatsapp:+14155238886";

    static {
        Twilio.init("AC49cab586f5ac4640f7cb608d67ad5bd7", "40c63946f933ccffbc5f75c3c99b1f39");
    }

//    static {  //need to check this method
//        Twilio.init(TwilioConfig.getTwilioAccountId(), TwilioConfig.getTwilioAccountAuthToken());
//    }


    public void sendWhatsAppMessage(String to, String message) {
        try {
            Message message1 = Message.creator(
                            new PhoneNumber("whatsapp:" + to),               // Recipient's WhatsApp number
                            new PhoneNumber(TWILIO_SANDBOX_NUMBER), // Twilio sandbox number for WhatsApp
                            message)                                 // Message content
                    .create();

            System.out.println("message1 is "+message1);

            System.out.println("WhatsApp message sent to: " + to);
        } catch (Exception e) {
            System.err.println("Error sending WhatsApp message to " + to + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

}
