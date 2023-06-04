package com.example.crud.controller;


import com.example.crud.dto.TaskDtoRequest;
import com.example.crud.dto.TaskDtoResponse;
import com.example.crud.service.TaskService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import reactor.core.publisher.Mono;



@Controller("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Post
    @Status(HttpStatus.CREATED)
    public Mono<TaskDtoResponse> addTasks(final TaskDtoRequest taskDtoRequest){
        return taskService.addTask(taskDtoRequest);
    }


    @Get("/{taskId}")
    @Status(HttpStatus.OK)
    public Mono<HttpResponse<TaskDtoResponse>> getTask(@PathVariable("taskId") final Long taskId){
        return taskService.getTest(taskId)
                .map(HttpResponse::ok);

    }

    @Delete("/{taskId}")
    @Status(HttpStatus.OK)
    public Mono<HttpResponse<Void>> deleteTask(@PathVariable("taskId") final Long taskId){
        return  taskService.deleteTask(taskId).thenReturn(HttpResponse.noContent());
    }

    @Put("/{taskId}")
    @Status(HttpStatus.OK)
    public Mono<HttpResponse<TaskDtoResponse>> updateTask(@PathVariable("taskId") final Long taskId,@Body final TaskDtoRequest taskDtoRequest){
        return taskService.updateTask(taskId,taskDtoRequest).map(HttpResponse::ok);
    }

}
