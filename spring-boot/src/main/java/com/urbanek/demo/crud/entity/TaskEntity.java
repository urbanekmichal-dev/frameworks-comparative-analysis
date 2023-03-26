package com.urbanek.demo.crud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;


@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskEntity {
    @Id
    private UUID taskId;
    @Column("name")
    private String name;
    @Column("description")
    private String description;
}
