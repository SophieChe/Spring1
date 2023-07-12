package com.example.newproject1.controllers;

import com.example.newproject1.domain.Status;
import com.example.newproject1.domain.Task;
import com.example.newproject1.repository.TaskRepository;
import com.example.newproject1.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaskController {
    @Autowired
    TaskService taskService;


    @GetMapping("/open")
    public String open(Model model) {
        List<Task> allTasks = taskService.getAll();
        model.addAttribute("allTasks", allTasks);
        return "mainPage";
    }

    @GetMapping("/openTask")
    public String openTask() {
        return "task";
    }

    @PostMapping("/createTask")
    public String createTask(@RequestParam(name = "description") String description,
                             @RequestParam(name = "status") String status, Model model) {
        Task task = new Task(description, Status.valueOf(status.toUpperCase()));
        taskService.addTask(task);
        return "redirect:/open";
    }

    @GetMapping("/task/delete/{id}")
    public String deleteTask(@PathVariable(name = "id") int id) {
        taskService.deleteTask(id);
        return "redirect:/open";
    }

    @GetMapping ("/task/edit/{id}")
    public String editTask(@PathVariable(name = "id") int id, Model model) {
       Task task = taskService.getTask(id);
       model.addAttribute("id", id);
       model.addAttribute("description", task.getDescription());
       model.addAttribute("status", task.getStatus());
       model.addAttribute("allstatus", Status.values());
        return "updateTask";
    }

    @PostMapping("/update")
    public String updatedTask(@RequestParam(name = "id") int id,
                              @RequestParam(name = "description") String description,
                              @RequestParam(name = "status") Status status){
        taskService.editTask(id, description, status);
        return "redirect:/open";
    }


}
