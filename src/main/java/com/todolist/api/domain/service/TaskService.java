package com.todolist.api.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.todolist.api.domain.model.Task;
import com.todolist.api.domain.repositories.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository repository;
	public List<Task> listar(){
		return repository.findAll();
	}
	
	public Task salvar(Task task) {
		return repository.save(task);
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	public Optional<Task> findById(@PathVariable Long id) {
		return repository.findById(id);
	}
}
