package com.example.newproject1.controllers;

import com.example.newproject1.domain.Status;
import com.example.newproject1.domain.Task;
import com.example.newproject1.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {
    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/open")
    public String open(){
        return "task";
    }

    @PostMapping("/createTask")
    public void createTask(@RequestParam(name="description") String description,
                             @RequestParam(name="status") String status, Model model){
        Task task = new Task(description, Status.valueOf(status.toUpperCase()));
        taskRepository.save(task);
    }
}
