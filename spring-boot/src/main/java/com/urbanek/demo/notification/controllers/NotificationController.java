package com.urbanek.demo.notification.controllers;

import com.urbanek.demo.notification.dto.NotificationDtoResponse;
import com.urbanek.demo.notification.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/datetime")
    public Mono<ResponseEntity<NotificationDtoResponse>> getDateTime(){
        return notificationService.getDateTime().map(ResponseEntity::ok);
    }

    @GetMapping("/datetime/{delay}")
    public Mono<ResponseEntity<NotificationDtoResponse>> getDelayedDateTime(@PathVariable final int delay){
        return notificationService.getDelayedDateTime(delay).map(ResponseEntity::ok);
    }

    @GetMapping(path="/datetimeSSE", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<NotificationDtoResponse> getDateTimeSSE(@RequestParam final int delay,@RequestParam final int count){
        return notificationService.getDateTimeSSE(delay, count);
    }
}
