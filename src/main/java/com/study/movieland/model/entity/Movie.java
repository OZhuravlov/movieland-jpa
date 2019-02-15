package com.study.movieland.model.entity;

import com.study.movieland.model.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "MOVIES")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_generator")
    @SequenceGenerator(name = "movie_generator", sequenceName = "seq_movies")
    int id;

    @Column(name = "name_native", nullable = false, length = 500)
    private String nameNative;

    @Column(name = "name_russian", nullable = false, length = 500)
    private String nameRussian;

    @Column(name = "year_of_release", nullable = false)
    private int yearOfRelease;

    @Column
    private double rating;

    @Column(name = "picture_path", length = 4000)
    private String picturePath;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_countries",
            joinColumns = {@JoinColumn(name = "movie_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "country_id", referencedColumnName = "id")})
    private volatile Set<Country> countries;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_genres",
            joinColumns = {@JoinColumn(name = "movie_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "id")})
    private volatile Set<Genre> genres;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", nullable = false)
    private volatile Set<Review> reviews;

    @Transient
    private Currency currency;

    @Transient
    private double priceInCurrency;

    public Movie(int id, String nameNative, String nameRussian, int yearOfRelease, Double rating, Double price, String picturePath) {
        this.id = id;
        this.nameNative = nameNative;
        this.nameRussian = nameRussian;
        this.yearOfRelease = yearOfRelease;
        this.rating = rating;
        this.price = price;
        this.picturePath = picturePath;
    }

    public Movie(Movie movieToCopy) {
        this.id = movieToCopy.id;
        this.nameNative = movieToCopy.nameNative;
        this.nameRussian = movieToCopy.nameRussian;
        this.yearOfRelease = movieToCopy.yearOfRelease;
        this.rating = movieToCopy.rating;
        this.price = movieToCopy.price;
        this.picturePath = movieToCopy.picturePath;
        this.currency = movieToCopy.currency;
        this.priceInCurrency = movieToCopy.priceInCurrency;
        this.description = movieToCopy.description;
        this.countries = new HashSet<>(movieToCopy.countries);
        this.genres = new HashSet<>(movieToCopy.genres);
        this.reviews = new HashSet<>(movieToCopy.reviews);
    }

}
