package com.example.notification.service;

import com.example.notification.dto.NotificationDtoResponse;
import jakarta.inject.Singleton;
@Singleton
public class NotificationConverter {

    public NotificationDtoResponse notificationStringToNotificationDtoResponse(final String message){
        NotificationDtoResponse response = new NotificationDtoResponse();
        response.setMessage(message);
        return response;
    }
}
