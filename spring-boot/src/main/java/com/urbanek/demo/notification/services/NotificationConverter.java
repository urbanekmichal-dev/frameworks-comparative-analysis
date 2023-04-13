package com.urbanek.demo.notification.services;

import com.urbanek.demo.notification.dto.NotificationDtoResponse;
import org.springframework.stereotype.Component;

@Component
public class NotificationConverter {

    public NotificationDtoResponse notificationStringToNotificationDtoResponse(final String message){
        NotificationDtoResponse response = new NotificationDtoResponse();
        response.setMessage(message);
        return response;
    }
}
