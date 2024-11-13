package com.krishna.Task_Management.payload;

import com.krishna.Task_Management.entity.Users;
import lombok.Data;

@Data
public class TaskDTO {

    private long taskId;
    private String taskName;
    private Users users;

}
