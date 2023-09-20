package com.rescue.vscube.notification;

import com.rescue.vscube.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/notification")
@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("")
    public ResponseEntity<Void> sendSMS() {
        notificationService.sendMessage("+14789795121","hello test message !!!");
        return ResponseEntity.ok(null);
    }
}
