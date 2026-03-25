package com.app.todoapp.controller;

import com.app.todoapp.services.TaskService;
import com.app.todoapp.models.Task;  // ✅ correct import
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/tasks")  // optional but better
public class TaskController<Task> {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTasks(Model model) {
        List<Task> tasks = (List<Task>) taskService.getAllTasks();
        model.addAttribute("tasks", tasks);  // ✅ fixed
        return "tasks";
    }

    @PostMapping
    public String createTasks(@RequestParam String title) {
        taskService.createTask(title);
        return "redirect:/";
    }
    @GetMapping("/{id}/delete")
    public String deleteTasks(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }
    @GetMapping("/{id}/toggle")
    public String toggleTasks(@PathVariable Long id) throws IllegalAccessException {
        taskService.toggleTask(id);
        return "redirect:/";
    }
}