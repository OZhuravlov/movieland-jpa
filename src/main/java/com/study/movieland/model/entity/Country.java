package com.study.movieland.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "COUNTRIES")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_generator")
    @SequenceGenerator(name = "country_generator", sequenceName = "seq_countries")
    private int id;
    @Column(nullable = false)
    private String name;
}
