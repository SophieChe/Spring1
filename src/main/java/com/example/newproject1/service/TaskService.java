package com.example.newproject1.service;

import com.example.newproject1.domain.Status;
import com.example.newproject1.domain.Task;
import com.example.newproject1.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public List<Task> getAll(){
        return taskRepository.findAll();
    }

    public void addTask(Task task){
        taskRepository.save(task);
    }

    public void deleteTask(int id){
        taskRepository.deleteById(id);
    }

    public void editTask(int id, String description, Status status){
       Task task = taskRepository.findById(id).get();
        task.setDescription(description);
        task.setStatus(status);
        taskRepository.save(task);
    }

    public Task getTask(int id){
        Task task = taskRepository.getById(id);
        return task;
    }

}
