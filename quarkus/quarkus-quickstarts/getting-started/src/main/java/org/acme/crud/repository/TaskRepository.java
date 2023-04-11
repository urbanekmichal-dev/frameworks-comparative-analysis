package org.acme.crud.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import org.acme.crud.entity.TaskEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class TaskRepository implements PanacheRepositoryBase<TaskEntity, UUID> {

}
