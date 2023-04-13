package com.example.crud.entity;

import lombok.Data;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import lombok.NoArgsConstructor;

import java.util.UUID;


@MappedEntity(value = "tasks1")
public class TaskEntity {



    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private Long id;
    private String name;
    private String description;

    public TaskEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
