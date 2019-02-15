package com.study.movieland.web.controller;

import com.study.movieland.model.entity.Country;
import com.study.movieland.model.entity.Genre;
import com.study.movieland.model.entity.Movie;
import com.study.movieland.repo.CountryRepository;
import com.study.movieland.repo.GenreRepository;
import com.study.movieland.repo.MovieRepository;
import com.study.movieland.web.data.MovieAddRequestData;
import com.study.movieland.web.data.MovieEditRequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/movie")
public class MovieController {

    private static final String RATING_FIELD_NAME = "rating";
    private static final String PRICE_FIELD_NAME = "price";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    MovieRepository<Movie, Integer> repository;
    GenreRepository<Genre, Integer> genreRepository;
    CountryRepository<Country, Integer> countryRepository;

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Iterable<Movie> getAllMovies() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Movie getMoviesById(@PathVariable int id) {
        logger.info("Get movies by id {}", id);
        Optional<Movie> optMovie = repository.findById(id);
        Movie movie = optMovie.get();
        logger.trace("Returning movie {}", movie);
        return movie;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public void addMovie(@RequestBody MovieAddRequestData movieAddRequestData) {
        logger.info("putMovie new movie");
        Movie movie = new Movie();
        movie.setNameNative(movieAddRequestData.getNameNative());
        movie.setNameRussian(movieAddRequestData.getNameRussian());
        movie.setYearOfRelease(movieAddRequestData.getYearOfRelease());
        movie.setDescription(movieAddRequestData.getDescription());
        movie.setPrice(movieAddRequestData.getPrice());
        movie.setPicturePath(movieAddRequestData.getPicturePath());
        movie.setCountries(getCountries(movieAddRequestData.getCountries()));
        movie.setGenres(getGenres(movieAddRequestData.getGenres()));
        logger.debug("putMovie new movie {}", movie);
        repository.save(movie);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public void editMovie(@RequestBody MovieEditRequestData movieEditRequestData,
                          @PathVariable int id) {
        logger.info("edit movie id {}", id);
        Movie movie = new Movie();
        movie.setId(id);
        movie.setNameNative(movieEditRequestData.getNameNative());
        movie.setNameRussian(movieEditRequestData.getNameRussian());
        movie.setPicturePath(movieEditRequestData.getPicturePath());
        movie.setCountries(getCountries(movieEditRequestData.getCountries()));
        movie.setGenres(getGenres(movieEditRequestData.getGenres()));
        logger.debug("edit movie {}", movie);
        repository.save(movie);
    }

    @Autowired
    public void setRepository(MovieRepository<Movie, Integer> repository) {
        this.repository = repository;
    }

    private Set<Country> getCountries(Set<Integer> countryIds) {
        return StreamSupport.stream(
                countryRepository.findAllById(countryIds).spliterator(), false)
                .collect(Collectors.toSet());
    }

    private Set<Genre> getGenres(Set<Integer> genreIds) {
        return StreamSupport.stream(
                genreRepository.findAllById(genreIds).spliterator(), false)
                .collect(Collectors.toSet());
    }
}

