package com.krishna.Task_Management.controller;

import com.krishna.Task_Management.payload.TaskDTO;
import com.krishna.Task_Management.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/{userId}/createTask")
    public ResponseEntity<TaskDTO> createTask(@PathVariable long userId ,
                                              @RequestBody TaskDTO taskDTO)
    {
        return new ResponseEntity<>
                (taskService.createTask(userId,taskDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/getAllTasksForUser")
    public ResponseEntity<List<TaskDTO>> getAllTasks(@PathVariable long userId
                                                        )
    {
        return new ResponseEntity<>
                (taskService.getAllTasks(userId),HttpStatus.FOUND);
    }

    @GetMapping("/{userId}/GetIndividualTask/{taskId}")
    public ResponseEntity<TaskDTO> getIndividualTaskForUser(@PathVariable long userId ,
                                                            @PathVariable long taskId)
    {
        return new ResponseEntity<>(taskService.getTaskForUser(userId,taskId),HttpStatus.FOUND);
    }

    @DeleteMapping("/{userId}/deleteTask/{taskId}")
    public ResponseEntity<String> deleteTaskForUser(@PathVariable long userId ,
                                                    @PathVariable long taskId)
    {
        taskService.deleteTaskForUser(userId, taskId);
        return new ResponseEntity<>("Task Deleted Successfully...!",HttpStatus.OK);
    }
}
