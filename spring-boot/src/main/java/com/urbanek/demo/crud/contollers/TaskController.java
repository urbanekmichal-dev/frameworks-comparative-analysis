package com.urbanek.demo.crud.contollers;

import com.urbanek.demo.crud.dto.TaskDtoRequest;
import com.urbanek.demo.crud.dto.TaskDtoResponse;
import com.urbanek.demo.crud.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    @PostMapping("/")
    public Mono<ResponseEntity<TaskDtoResponse>> addTask(@RequestBody final TaskDtoRequest taskDtoRequest){
        return taskService.addTask(taskDtoRequest)
                .map(ResponseEntity::ok);
    }

    @GetMapping ("/")
    public Flux<TaskDtoResponse> getAllTasks(){
        return taskService.getTasks();

    }

    @GetMapping("/{taskId}")
    public Mono<ResponseEntity<TaskDtoResponse>> getTask(@PathVariable final UUID taskId){
        return taskService.getTask(taskId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping ("/{taskId}")
    public Mono<Void> deleteTask(@PathVariable final UUID taskId){
        return taskService.deleteTask(taskId);
    }

    @PutMapping("/{taskId}")
    public Mono<ResponseEntity<TaskDtoResponse>> updateTask(@PathVariable final UUID taskId, @RequestBody final TaskDtoRequest taskDtoRequest){
        return taskService.updateTask(taskId,taskDtoRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}

