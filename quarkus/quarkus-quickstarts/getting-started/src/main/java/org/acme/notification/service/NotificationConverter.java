package org.acme.notification.service;

import org.acme.notification.dto.NotificationDtoResponse;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotificationConverter {

    public NotificationDtoResponse notificationStringToNotificationDtoResponse(final String message){
        NotificationDtoResponse response = new NotificationDtoResponse();
        response.setMessage(message);
        return response;
    }
}
