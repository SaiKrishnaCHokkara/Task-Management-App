package com.krishna.Task_Management.service.impl;

import com.krishna.Task_Management.entity.Tasks;
import com.krishna.Task_Management.entity.Users;
import com.krishna.Task_Management.exceptions.APIException;
import com.krishna.Task_Management.exceptions.ExceptionMessages;
import com.krishna.Task_Management.exceptions.TaskNotFoundException;
import com.krishna.Task_Management.exceptions.UserNotFoundException;
import com.krishna.Task_Management.payload.TaskDTO;
import com.krishna.Task_Management.repository.TaskRepo;
import com.krishna.Task_Management.repository.UserRepo;
import com.krishna.Task_Management.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TaskDTO createTask(long userId, TaskDTO taskDTO) {
      taskDTO.setUsers(
              userRepo.findById(userId)
                .orElseThrow(
                ()-> new UserNotFoundException( "User not found in data base id : "+userId)
        ));
       Tasks task =modelMapper.map(taskDTO, Tasks.class);
        taskRepo.save(task);

        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public List<TaskDTO> getAllTasks(long userId) {
log.info("Checking Weather user present in db or not");
       userRepo.findById(userId).orElseThrow(
                ()-> new UserNotFoundException(ExceptionMessages.User_Not_Found)
        );
 log.info("User present in database");

 List<Tasks> tasks = taskRepo.findAllByUsers_UserId(userId);
    return tasks.stream()
            .map(task -> modelMapper.map(task, TaskDTO.class))
            .collect(Collectors.toList());


    }

    @Override
    public TaskDTO getTaskForUser(long userId, long taskId) {

        Users user = userRepo.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ExceptionMessages.User_Not_Found)
        );

        Tasks task = taskRepo.findById(taskId).orElseThrow(
                ()-> new TaskNotFoundException(ExceptionMessages.Task_Not_Found)
        );

        if(user.getUserId() != task.getUsers().getUserId())
        { throw new APIException(ExceptionMessages.API_Exception);}
       else
        { return modelMapper.map(task, TaskDTO.class);}
    }

    @Override
    public void deleteTaskForUser(long userId, long taskId) {
        Users user = userRepo.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ExceptionMessages.User_Not_Found)
        );

        Tasks task = taskRepo.findById(taskId).orElseThrow(
                ()-> new TaskNotFoundException(ExceptionMessages.Task_Not_Found)
        );

        if(user.getUserId() != task.getUsers().getUserId())
        {
            throw new TaskNotFoundException(ExceptionMessages.Task_Not_Found);
        }
        else
        {
            taskRepo.deleteById(task.getTaskId());
        }
    }


}
