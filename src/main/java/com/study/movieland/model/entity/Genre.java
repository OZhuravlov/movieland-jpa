package com.study.movieland.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "GENRES")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_generator")
    @SequenceGenerator(name = "genre_generator", sequenceName = "seq_genres")
    private int id;
    @Column(nullable = false)
    private String name;

}
