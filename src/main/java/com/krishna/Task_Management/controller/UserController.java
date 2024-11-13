package com.krishna.Task_Management.controller;

import com.krishna.Task_Management.payload.UserDTO;
import com.krishna.Task_Management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/createUser")
    public ResponseEntity<UserDTO> registerUsers(@RequestBody UserDTO userDTO)
    {
        log.info("Create User api called ");
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers()
    {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long userId)
    {
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.FOUND);
    }

}
