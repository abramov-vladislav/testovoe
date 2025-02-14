package org.abramov.spring.testovoe.taskservice.controller;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.controller.dto.TaskDTO;
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
    public ResponseEntity<List<TaskDTO>> getAllTasks(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        final var tasks = taskService.getAllTasks(pageNumber, pageSize);
        final var taskDTOList = tasks.stream().map(task -> TaskMapper.toTaskDTO(task)).toList();

        return ResponseEntity.ok(taskDTOList);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@RequestParam UUID taskId) {
        final var task = taskService.getTaskByTaskId(taskId);

        return ResponseEntity.ok(TaskMapper.toTaskDTO(task));
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        UUID taskId = UUID.randomUUID();
        final var task = taskService.createTask(
                taskId,
                taskDTO.getTaskName(),
                taskDTO.getTaskOwnerId(),
                taskDTO.getTaskExecutorId(),
                taskDTO.getTaskStatus(),
                taskDTO.getTaskCreateDate(),
                taskDTO.getTaskUpdateDate()
        );
        return ResponseEntity.ok(TaskMapper.toTaskDTO(task));
    }

    @PostMapping("/{co")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable UUID taskId, @RequestBody TaskDTO taskDTO) {
        final var task = taskService.updateTask(
                taskId,
                taskDTO.getTaskName(),
                taskDTO.getTaskOwnerId(),
                taskDTO.getTaskExecutorId(),
                taskDTO.getTaskStatus(),
                taskDTO.getTaskCreateDate(),
                taskDTO.getTaskUpdateDate()
        );
        return ResponseEntity.ok(TaskMapper.toTaskDTO(task));
    }


}
