package com.krishna.Task_Management.repository;

import com.krishna.Task_Management.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Tasks,Long> {
    List<Tasks> findAllByUsers_UserId(long userId);
}
