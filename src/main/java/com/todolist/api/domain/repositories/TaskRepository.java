package com.todolist.api.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist.api.domain.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
}
