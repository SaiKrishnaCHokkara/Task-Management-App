package com.krishna.Task_Management.service;

import com.krishna.Task_Management.payload.TaskDTO;

import java.util.List;

public interface TaskService {

    TaskDTO createTask(long userId, TaskDTO taskDTO);

    List<TaskDTO> getAllTasks(long userId);

    TaskDTO getTaskForUser(long userId, long taskId);

    void deleteTaskForUser(long userId , long taskId);
}
