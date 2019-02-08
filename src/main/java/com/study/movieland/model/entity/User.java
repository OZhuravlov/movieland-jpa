package com.study.movieland.model.entity;

import com.study.movieland.model.Role;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "seq_users")
    private int id;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String sole;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}