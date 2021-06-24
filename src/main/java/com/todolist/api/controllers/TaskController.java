package com.todolist.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.todolist.api.domain.model.Task;
import com.todolist.api.domain.repositories.TaskRepository;
import com.todolist.api.domain.service.TaskService;

@RestController
@RequestMapping("tasks")
@CrossOrigin(origins = "*")
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@GetMapping
	public List<Task> listar(){
		return service.listar();
	}
	
	@GetMapping("orderByDescricao")
	public List<Task> listarPorDescricao(){
		return service.findAllAndOrderByDescricao();
	}
	
	@GetMapping("orderByData")
	public List<Task> listarPorData(){
		return service.findAllAndOrderByData();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> BuscarPorId(@PathVariable Long id) {
		Task task = service.findById(id);
			return ResponseEntity.ok(task);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Task salvar(@RequestBody Task task) {
		return service.salvar(task);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> atualizarTarefa(@RequestBody Task task) {
		service.atualizar(task);
		return ResponseEntity.ok().body(task);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
}
