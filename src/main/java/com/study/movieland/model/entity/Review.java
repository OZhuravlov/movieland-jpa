package com.study.movieland.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "REVIEWS")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_generator")
    @SequenceGenerator(name = "review_generator", sequenceName = "seq_reviews")
    private int id;
    @Column(nullable = false, length = 4000)
    private String text;
    @ManyToOne(optional = false)
    private User user;
}
