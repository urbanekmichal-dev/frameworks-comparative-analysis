package com.example.crud.service;

import com.example.crud.coverter.TaskConverter;
import com.example.crud.dto.TaskDtoRequest;
import com.example.crud.dto.TaskDtoResponse;
import com.example.crud.entity.TaskEntity;
import com.example.crud.repository.TaskRepository;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


import java.util.UUID;

@Singleton

public class TaskService {

    private TaskRepository taskRepository;

    private TaskConverter taskConverter;

    public TaskService(TaskRepository taskRepository, TaskConverter taskConverter) {
        this.taskRepository = taskRepository;
        this.taskConverter = taskConverter;
    }

    public Mono<TaskDtoResponse> addTask(final TaskDtoRequest taskDtoRequest){
        return taskRepository.save(taskConverter.convertTaskDtoRequestToTaskEntity(taskDtoRequest)).
                map(taskConverter::convertTaskEntityToTaskDtoResponse);
    }
    public Mono<TaskDtoResponse> getTest(final Long taskId){
        return taskRepository.findById(taskId).map(taskConverter::convertTaskEntityToTaskDtoResponse);
    }

    public Mono<Long> deleteTask(final Long taskId){
        return taskRepository.deleteById(taskId);
    }

    public Mono<TaskDtoResponse> updateTask(final Long taskId, final TaskDtoRequest taskDtoRequest){
        Mono<TaskEntity> taskMono = taskRepository.findById(taskId);

        return taskMono.flatMap(existTask-> {
            existTask.setName(taskDtoRequest.getName());
            existTask.setDescription(taskDtoRequest.getDescription());
            return taskRepository.update(existTask);
        }).map(task -> taskConverter.convertTaskEntityToTaskDtoResponse(task));
    }
}
