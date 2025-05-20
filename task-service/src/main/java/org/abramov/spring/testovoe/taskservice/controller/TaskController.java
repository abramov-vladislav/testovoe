package org.abramov.spring.testovoe.taskservice.controller;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.dto.request.CreateTaskDto;
import org.abramov.spring.testovoe.taskservice.dto.request.UpdateTaskDto;
import org.abramov.spring.testovoe.taskservice.dto.response.TaskDto;
import org.abramov.spring.testovoe.taskservice.mapper.TaskMapper;
import org.abramov.spring.testovoe.taskservice.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TaskDto>> getAllTasks(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        final var tasks = taskService.getAllTasks(pageNumber, pageSize);
        final var taskDtoList = tasks.stream().map(task -> TaskMapper.toTaskDto(task)).toList();

        return ResponseEntity.ok(taskDtoList);
    }

    @GetMapping("/owner/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TaskDto>> getAllTasksByUserIdAsOwner(@PathVariable UUID userId, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        final var tasks = taskService.getAllTasksByUserIdAsOwner(userId, pageNumber, pageSize);
        final var taskDtoList = tasks.stream().map(task -> TaskMapper.toTaskDto(task)).toList();

        return ResponseEntity.ok(taskDtoList);
    }

    @GetMapping("/executor/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TaskDto>> getAllTasksByUserIdAsExecutor(@PathVariable UUID userId, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        final var tasks = taskService.getAllTasksByUserIdAsExecutor(userId, pageNumber, pageSize);
        final var taskDtoList = tasks.stream().map(task -> TaskMapper.toTaskDto(task)).toList();

        return ResponseEntity.ok(taskDtoList);
    }

    @GetMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TaskDto> getTaskById(@PathVariable UUID taskId) {
        final var task = taskService.getTaskByTaskId(taskId);

        return ResponseEntity.ok(TaskMapper.toTaskDto(task));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TaskDto> createTask(@RequestBody CreateTaskDto createTaskDto) {
        final var task = taskService.createTask(createTaskDto);

        return ResponseEntity.ok(TaskMapper.toTaskDto(task));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable UUID taskId, @RequestBody UpdateTaskDto updateTaskDto) {
        final var task = taskService.updateTask(TaskMapper.toTask(taskId, updateTaskDto));

        return ResponseEntity.ok(TaskMapper.toTaskDto(task));
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
    }

}
