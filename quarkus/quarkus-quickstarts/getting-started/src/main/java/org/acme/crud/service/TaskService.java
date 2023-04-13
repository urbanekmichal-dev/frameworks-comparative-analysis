package org.acme.crud.service;

import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import org.acme.crud.coverter.TaskConverter;
import org.acme.crud.dto.TaskDtoRequest;
import org.acme.crud.dto.TaskDtoResponse;
import org.acme.crud.repository.TaskRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;

    private TaskConverter taskConverter;

    public Uni<TaskDtoResponse> addTask(final TaskDtoRequest taskDtoRequest){
        return taskRepository.persistAndFlush(taskConverter.convertTaskDtoRequestToTaskEntity(taskDtoRequest)).
                map(taskConverter::convertTaskEntityToTaskDtoResponse);
    }
    public Uni<TaskDtoResponse> getTest(final UUID taskId){
        return taskRepository.findById(taskId).map(taskConverter::convertTaskEntityToTaskDtoResponse);
    }
    @ReactiveTransactional
    public Uni<Boolean> deleteTask(final UUID taskId){
        var res =taskRepository.deleteById(taskId);
        taskRepository.flush();
        return res;
    }
    @ReactiveTransactional
    public Uni<TaskDtoResponse> updateTask(final UUID taskId, final TaskDtoRequest taskDtoRequest){
       return  taskRepository.findById(taskId).onItem().ifNotNull().transform(e->{
                e.setName(taskDtoRequest.getName());
                e.setDescription(taskDtoRequest.getDescription());
                return e;
                }).onFailure().recoverWithNull().map(taskConverter::convertTaskEntityToTaskDtoResponse);

    }
}
