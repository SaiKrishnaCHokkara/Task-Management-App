package com.krishna.Task_Management.repository;

import com.krishna.Task_Management.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<Users,Long> {
}
