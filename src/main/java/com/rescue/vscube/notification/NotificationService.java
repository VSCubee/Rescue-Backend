package com.rescue.vscube.notification;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {
    public void sendMessage(String phone, String message){
        Twilio.init("AC2375d68fdc423c3697fa4a6b83d8d551","09f8fc1c00fd8071f640ad14f22b2b81");
        Message.creator(new PhoneNumber(phone),
                new PhoneNumber("+14789795121"), message).create();
    }
}
