package com.urbanek.demo.crud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Table(name = "entities")
@NoArgsConstructor
@Data
public class TaskEntity {
    @Id
    @Column(name = "task_id")
    private UUID taskId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
}
