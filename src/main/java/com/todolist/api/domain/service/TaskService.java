package com.todolist.api.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.api.domain.model.Task;
import com.todolist.api.domain.repositories.TaskRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository repository;

	public List<Task> listar() {
		return repository.findAll();
	}
	
	public List<Task> findAllAndOrderByDescricao() {
		return repository.findAllAndOrderByDescricao();
	}
	
	public List<Task> findAllAndOrderByData() {
		return repository.findAllAndOrderByData();
	}

	public Task salvar(Task task) {
		Optional<Task> optTask = Optional.of(repository.save(task));
		return optTask.orElseThrow(() -> new com.todolist.api.domain.service.exceptions.ObjectNotFoundException(
				"Não foi possível criar esta tarefa: " + task.getDescricao()));
	}
	
	public Task atualizar(Task task) {
		Task objTask = findById(task.getId());
		objTask.setDescricao(task.getDescricao());
		objTask.setData(task.getData());
		objTask.setConcluida(task.isConcluida());
		return repository.save(objTask);
	}
	

	public void deletar(Long id) {
		Task task = findById(id);
		repository.delete(task);
	}

	public Task findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new com.todolist.api.domain.service.exceptions.ObjectNotFoundException(
				"Tarefa não encontrada! Id: " + id));
	}
	
	
}
