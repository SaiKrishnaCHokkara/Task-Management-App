package com.krishna.Task_Management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long userId;

    @Column( nullable = false)
    private String userName;

    @Column(unique = true , nullable = false)
    private String gmail;

    @Column( nullable = false)
    private String password;
}
