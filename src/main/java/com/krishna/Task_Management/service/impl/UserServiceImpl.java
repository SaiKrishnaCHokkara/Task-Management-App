package com.krishna.Task_Management.service.impl;

import com.krishna.Task_Management.entity.Users;
import com.krishna.Task_Management.exceptions.UserNotFoundException;
import com.krishna.Task_Management.payload.UserDTO;
import com.krishna.Task_Management.repository.UserRepo;
import com.krishna.Task_Management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;



    @Override
    public UserDTO createUser(UserDTO userDTO) {
       log.info("Converting UserDto to Users to save data in db");
      Users savedUser = userRepo.save(modelMapper.map(userDTO, Users.class));
        log.info("Converting Saved User to UserDTO to easy access the data");
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers()
    {


       // UserDTO dto= modelMapper.map(users, UserDTO.class);

        return userRepo.findAll().stream()
                .map(user-> modelMapper.map(user, UserDTO.class))
                 .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(long userId)
    {
        Optional<Users> user = userRepo.findById(userId);

        if(user.isPresent())
        {
            return  modelMapper.map(user, UserDTO.class);
        }
        else {
            throw new UserNotFoundException("User not found in data base id : "+userId);
        }


    }
}
