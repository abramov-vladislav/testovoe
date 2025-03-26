package org.abramov.spring.testovoe.taskservice.controller;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.controller.dto.model.TaskDto;
import org.abramov.spring.testovoe.taskservice.controller.mapper.TaskMapper;
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

    @GetMapping("/")
    public ResponseEntity<List<TaskDto>> getAllTasks(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        final var tasks = taskService.getAllTasks(pageNumber, pageSize);
        final var taskDtoList = tasks.stream().map(task -> TaskMapper.toTaskDto(task)).toList();

        return ResponseEntity.ok(taskDtoList);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@RequestParam UUID taskId) {
        final var task = taskService.getTaskByTaskId(taskId);

        return ResponseEntity.ok(TaskMapper.toTaskDto(task));
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        UUID taskId = UUID.randomUUID();
        final var task = taskService.createTask(
                taskId,
                taskDto.getTaskName(),
                taskDto.getTaskOwnerId(),
                taskDto.getTaskExecutorId(),
                taskDto.getTaskStatus(),
                taskDto.getTaskCreateDate(),
                taskDto.getTaskUpdateDate()
        );
        return ResponseEntity.ok(TaskMapper.toTaskDto(task));
    }

    @PostMapping("/{co")
    public ResponseEntity<TaskDto> updateTask(@PathVariable UUID taskId, @RequestBody TaskDto taskDto) {
        final var task = taskService.updateTask(
                taskId,
                taskDto.getTaskName(),
                taskDto.getTaskOwnerId(),
                taskDto.getTaskExecutorId(),
                taskDto.getTaskStatus(),
                taskDto.getTaskCreateDate(),
                taskDto.getTaskUpdateDate()
        );
        return ResponseEntity.ok(TaskMapper.toTaskDto(task));
    }


}
