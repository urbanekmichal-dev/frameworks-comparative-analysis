package com.urbanek.demo.crud.services;

import com.urbanek.demo.crud.dto.TaskDto;
import com.urbanek.demo.crud.dto.TaskDtoCreateResponse;
import com.urbanek.demo.crud.entity.TaskEntity;
import com.urbanek.demo.crud.services.converters.TaskConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    public Mono<TaskDto> getTask(final UUID taskId){
        return taskRepository.findByTaskId(taskId).mapNotNull(taskConverter::convertTaskEntityToTaskDto);
    }
    public Mono<TaskDtoCreateResponse> addTask(final TaskDto taskDto){
        return taskRepository.save(taskConverter.convertTaskDtoToTaskEntity(taskDto)).mapNotNull(taskConverter::convertTaskDtoToTaskEntity);
    }


}
