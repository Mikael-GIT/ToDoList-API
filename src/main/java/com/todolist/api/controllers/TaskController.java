package com.todolist.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.api.domain.model.Task;
import com.todolist.api.domain.repositories.TaskRepository;
import com.todolist.api.domain.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@Autowired
	private TaskRepository repository;
	
	
	@GetMapping
	public List<Task> listar(){
		return service.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> BuscarPorId(@PathVariable Long id) {
		Optional<Task> task = service.findById(id);
		if(task.isPresent()) {
			return ResponseEntity.ok(task.get());
		}
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Task salvar(@RequestBody Task task) {
		return service.salvar(task);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> atualizarAtividade(@PathVariable Long id, @RequestBody Task task) {
		if(!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		task = service.salvar(task);
		return ResponseEntity.ok(task);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if(!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		} 
			service.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
}
