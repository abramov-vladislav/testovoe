package org.abramov.spring.testovoe.taskservice.controller;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.model.Task;
import org.abramov.spring.testovoe.taskservice.service.TaskService;
import org.abramov.spring.testovoe.userservice.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/{tasks}")
    public List<Task> getAllTasks(@PathVariable String tasks) {
        return taskService.getAllTasks();
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskByTaskId(@PathVariable UUID taskId) {
        Task task = taskService.getTaskByTaskId(taskId);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/name/{taskName}")
    public ResponseEntity<Task> createTask(@PathVariable String taskName) {
        final var task = taskService.getTaskByTaskName(taskName);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/owner/{taskId}")
    public ResponseEntity<Task> getTasksByOwner(@PathVariable UUID userId, @PathVariable String taskId) {
        List<Task> tasks = taskService.getTasksByOwner(userId);
        return ResponseEntity.ok((Task) tasks);
    }

    @GetMapping("/executor/{taskId}")
    public ResponseEntity<Task> getTasksByExecutor(@PathVariable UUID userId, @PathVariable String taskId) {
        final var tasks = taskService.getTasksByExecutor(userId);
        return ResponseEntity.ok((Task) tasks);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return createdTask;
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@RequestBody UUID taskId, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(taskId, task);
        return ResponseEntity.ok(updatedTask);
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<Task> changeTaskStatus(@PathVariable UUID taskId, @RequestBody Task.TaskStatus status) {
        Task updatedTask = taskService.changeTaskStatus(taskId, status);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable UUID userId) {
        User user = taskService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
}
