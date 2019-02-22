package com.study.movieland.model.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@Table(name = "REVIEWS")
@NamedEntityGraph(name = "Review.detail",
        attributeNodes = {@NamedAttributeNode("user")}
)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_generator")
    @SequenceGenerator(name = "review_generator", sequenceName = "seq_reviews")
    private int id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(nullable = false, length = 4000)
    private String text;

    @ManyToOne(optional = false)
    private User user;

}
