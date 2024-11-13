package com.krishna.Task_Management.service;

import com.krishna.Task_Management.payload.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(long userId);
}
