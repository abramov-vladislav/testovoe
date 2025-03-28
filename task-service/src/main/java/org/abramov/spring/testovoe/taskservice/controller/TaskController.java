package org.abramov.spring.testovoe.taskservice.controller;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.dto.request.CreateTaskDto;
import org.abramov.spring.testovoe.taskservice.dto.request.UpdateTaskDto;
import org.abramov.spring.testovoe.taskservice.dto.response.TaskDto;
import org.abramov.spring.testovoe.taskservice.mapper.TaskMapper;
import org.abramov.spring.testovoe.taskservice.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/all")
    public ResponseEntity<List<TaskDto>> getAllTasks(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        final var tasks = taskService.getAllTasks();
        final var taskDtoList = tasks.stream().map(task -> TaskMapper.toTaskDto(task)).toList();

        return ResponseEntity.ok(taskDtoList);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable UUID taskId) {
        final var task = taskService.getTaskByTaskId(taskId);

        return ResponseEntity.ok(TaskMapper.toTaskDto(task));
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody CreateTaskDto createTaskDto) {
        final var task = taskService.createTask(createTaskDto);
        return ResponseEntity.ok(TaskMapper.toTaskDto(task));
    }

    @PostMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable UUID taskId, @RequestBody UpdateTaskDto updateTaskDto) {
        final var task = taskService.updateTask(TaskMapper.toTask(taskId, updateTaskDto));
        return ResponseEntity.ok(TaskMapper.toTaskDto(task));
    }

}
