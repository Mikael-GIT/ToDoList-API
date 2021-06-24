package com.todolist.api.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.todolist.api.domain.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
	@Query("SELECT task FROM Task task ORDER BY task.descricao")
	public List<Task> findAllAndOrderByDescricao();
	
	@Query("SELECT task FROM Task task ORDER BY task.data")
	public List<Task> findAllAndOrderByData();
}
