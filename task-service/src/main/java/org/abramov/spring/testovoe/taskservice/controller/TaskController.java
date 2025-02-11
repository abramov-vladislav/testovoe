package org.abramov.spring.testovoe.taskservice.controller;

import lombok.RequiredArgsConstructor;
import org.abramov.spring.testovoe.taskservice.controller.dto.TaskDTO;
import org.abramov.spring.testovoe.taskservice.controller.mapper.TaskMapper;
import org.abramov.spring.testovoe.taskservice.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        final var tasks = taskService.getAllTasks(pageNumber, pageSize);
        final var taskDTOList = tasks.stream().map(task -> TaskMapper.toTaskDTO(task)).toList();
        return ResponseEntity.ok(taskDTOList);
    }

}
