package org.abramov.spring.testovoe.taskservice.controller;
import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.controller.dto.TaskDTO;
import org.abramov.spring.testovoe.taskservice.controller.enumController.TaskStatus;
import org.abramov.spring.testovoe.taskservice.model.Task;
import org.abramov.spring.testovoe.taskservice.service.TaskService;
import org.abramov.spring.testovoe.userservice.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskByTaskId(@PathVariable UUID taskId) {
        Task task = taskService.getTaskByTaskId(taskId);
        return ResponseEntity.ok(task);
    }

    @GetMapping
    public List<TaskDTO> getAllTasks(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return taskService.getAllTasks();
    }

    @PostMapping("/name/{taskName}")
    public ResponseEntity<Task> createTask(@PathVariable TaskDTO taskDTO) {
        final var taskId = UUID.randomUUID();
        final var task = taskService.createTask(
                taskId
//ДОПОЛНИТЬ
        );
        return ResponseEntity.ok(task);
    }

    @GetMapping("/owner/{taskId}")
    public ResponseEntity<Task> getTasksByOwnerId(@PathVariable UUID userId, @PathVariable String taskId) {
        List<Task> tasks = taskService.getTasksByOwner(userId);
        return ResponseEntity.ok((Task) tasks);
    }

    @GetMapping("/executor/{taskId}")
    public ResponseEntity<Task> getTasksByExecutorId(@PathVariable UUID userId, @PathVariable String taskId) {
        final var tasks = taskService.getTasksByExecutor(userId);
        return ResponseEntity.ok((Task) tasks);
    }


    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@RequestBody UUID taskId, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(taskId, task);
        return ResponseEntity.ok(updatedTask);
    }

    @PatchMapping("/{taskId}/status")
    public ResponseEntity<Task> changeTaskStatus(@PathVariable UUID taskId, @RequestBody TaskStatus taskStatus) {
        Task updatedTask = taskService.changeTaskStatus(taskId, taskStatus);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserByUserId(@PathVariable UUID userId) {
        User user = taskService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
}
