package com.example.crud.repository;

import com.example.crud.entity.TaskEntity;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Mono;


@R2dbcRepository(dialect = Dialect.POSTGRES)
public interface TaskRepository extends ReactorCrudRepository<TaskEntity, Long> {
    Mono<Boolean> existsByName(String name);
}
