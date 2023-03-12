package com.urbanek.demo.crud.contollers;

import com.urbanek.demo.crud.dto.TaskDto;
import com.urbanek.demo.crud.dto.TaskDtoCreateResponse;
import com.urbanek.demo.crud.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    @PostMapping("/")
    public Mono<ResponseEntity<TaskDtoCreateResponse>> addTask(@RequestBody final TaskDto taskDto){
        return taskService.addTask(taskDto).map(ResponseEntity::ok);
    }

    @GetMapping("/{taskId}")
    public Mono<ResponseEntity<TaskDto>> getTask(@PathVariable final UUID taskId){
        return taskService.getTask(taskId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
