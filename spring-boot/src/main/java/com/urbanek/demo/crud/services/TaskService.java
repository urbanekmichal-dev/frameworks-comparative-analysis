package com.urbanek.demo.crud.services;

import com.urbanek.demo.crud.dto.TaskDtoRequest;
import com.urbanek.demo.crud.dto.TaskDtoResponse;
import com.urbanek.demo.crud.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    public Mono<TaskDtoResponse> getTask(final UUID taskId){
        return taskRepository.findByTaskId(taskId).mapNotNull(taskConverter::convertTaskEntityToTaskDtoResponse);
    }
    public Flux<TaskDtoResponse> getTasks(){
        return taskRepository.findAll().mapNotNull(taskConverter::convertTaskEntityToTaskDtoResponse);
    }
    public Mono<TaskDtoResponse> addTask(final TaskDtoRequest taskDtoRequest){
        return taskRepository.save(taskConverter.convertTaskDtoRequestToTaskEntity(taskDtoRequest)).mapNotNull(taskConverter::convertTaskEntityToTaskDtoResponse);
    }

    public Mono<Void> deleteTask(final UUID taskId){
        return taskRepository.deleteByTaskId(taskId);
    }
    public Mono<TaskDtoResponse> updateTask(final UUID taskId, final TaskDtoRequest taskDtoRequest){
        return taskRepository.findByTaskId(taskId).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalTask ->{
                    if(optionalTask.isPresent()){
                        TaskEntity taskEntity = taskConverter.convertTaskDtoRequestToTaskEntity(taskDtoRequest);
                        taskEntity.setTaskId(taskId);
                        return taskRepository.save(taskEntity);
                    }
                    return Mono.empty();
                }).mapNotNull((taskConverter::convertTaskEntityToTaskDtoResponse));
    }


}
