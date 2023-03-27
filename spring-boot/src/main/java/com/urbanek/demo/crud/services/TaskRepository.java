package com.urbanek.demo.crud.services;

import com.urbanek.demo.crud.entity.TaskEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface TaskRepository extends R2dbcRepository<TaskEntity, UUID> {

    Mono<TaskEntity> findByTaskId(UUID taskId);
    Mono<Void> deleteByTaskId(UUID taskId);
}
