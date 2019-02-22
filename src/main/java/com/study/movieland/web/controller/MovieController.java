package com.study.movieland.web.controller;

import com.study.movieland.dto.MovieInfo;
import com.study.movieland.dto.MovieInfoByCountry;
import com.study.movieland.dto.MovieInfoByGenre;
import com.study.movieland.dto.MovieInfoFindAll;
import com.study.movieland.repo.MovieRepository;
import com.study.movieland.web.exception.BadRequestParamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value = "/movie")
public class MovieController {

    private static final String SORT_PARAM_NAME = "sort";
    private static final String DIRECTION_PARAM_NAME = "direction";
    private static final String DEFAULT_SORT_FIELD = "Id";
    private static final String DEFAULT_SORT_DIRECTION = "ASC";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    MovieRepository repository;

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<MovieInfoFindAll> getAllMovies(
            @RequestParam(value = SORT_PARAM_NAME, required = false, defaultValue = DEFAULT_SORT_FIELD) String sorting,
            @RequestParam(value = DIRECTION_PARAM_NAME, required = false, defaultValue = DEFAULT_SORT_DIRECTION) String direction) {
        List<MovieInfoFindAll> movies = repository.findAllProjectedBy(Sort.by(Sort.Direction.fromString(direction), sorting));
        return movies;
    }

    @RequestMapping(value = "/search/{search}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<MovieInfo> getMoviesSearch(
            @PathVariable String search,
            @RequestParam(value = SORT_PARAM_NAME, required = false, defaultValue = DEFAULT_SORT_FIELD) String sorting,
            @RequestParam(value = DIRECTION_PARAM_NAME, required = false, defaultValue = DEFAULT_SORT_DIRECTION) String direction) {
        List<MovieInfo> movies = repository.findByNameNativeLikeIgnoreCase("%" + search + "%");
        movies.addAll(repository.findByNameRussianLikeIgnoreCase("%" + search + "%"));
        return movies;
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<MovieInfoFindAll> getRandomMovies() {
        int targetRandomSize = 3;
        List<MovieInfoFindAll> movies = new ArrayList<>();
        List<MovieInfoFindAll> allMovies = repository.findAllProjectedBy(Sort.unsorted());
        while (movies.size() < allMovies.size() && movies.size() < targetRandomSize) {
            int random = ThreadLocalRandom.current().nextInt(allMovies.size());
            MovieInfoFindAll movie = allMovies.get(random);
            if (!movies.contains(movie)) {
                movies.add(allMovies.get(random));
            }
        }
        return movies;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public MovieInfo getMoviesById(@PathVariable int id) {
        logger.info("Get movies by id {}", id);
        Optional<MovieInfo> optMovie = repository.getById(id);
        if (optMovie.isPresent()) {
            MovieInfo movie = optMovie.get();
            logger.trace("Returning movie {}", movie);
            return movie;
        } else {
            throw new BadRequestParamException("Movie with id " + id + " does not exist");
        }
    }

    @RequestMapping(value = "/genre/{genreId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<MovieInfoByGenre> getMoviesByGenre(@PathVariable Integer genreId,
                                                   @RequestParam(value = SORT_PARAM_NAME, required = false, defaultValue = DEFAULT_SORT_FIELD) String
                                                           sorting,
                                                   @RequestParam(value = DIRECTION_PARAM_NAME, required = false, defaultValue = DEFAULT_SORT_DIRECTION) String
                                                           direction) {
        logger.info("Get movies by genre");
        List<MovieInfoByGenre> movies = repository.findByGenres_Id(genreId,
                Sort.by(Sort.Direction.fromString(direction),
                        sorting));
        logger.debug("Returning {} movie(s) for genreId {}", movies.size(), genreId);
        return movies;
    }

    @RequestMapping(value = "/country/{countryId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<MovieInfoByCountry> getMoviesByCountry(@PathVariable Integer countryId,
                                                       @RequestParam(value = SORT_PARAM_NAME, required = false, defaultValue = DEFAULT_SORT_FIELD) String
                                                               sorting,
                                                       @RequestParam(value = DIRECTION_PARAM_NAME, required = false, defaultValue = DEFAULT_SORT_DIRECTION) String
                                                               direction) {
        logger.info("Get movies by country");
        List<MovieInfoByCountry> movies = repository.findByCountries_Id(countryId,
                Sort.by(Sort.Direction.fromString(direction),
                        sorting));
        logger.debug("Returning {} movie(s) for countryId {}", movies.size(), countryId);
        return movies;
    }

    @RequestMapping(value = "/year/{year}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<MovieInfo> getMoviesByYear(@PathVariable Integer year,
                                           @RequestParam(value = SORT_PARAM_NAME, required = false, defaultValue = DEFAULT_SORT_FIELD) String
                                                   sorting,
                                           @RequestParam(value = DIRECTION_PARAM_NAME, required = false, defaultValue = DEFAULT_SORT_DIRECTION) String
                                                   direction) {
        logger.info("Get movies by genre");
        List<MovieInfo> movies = repository.findByYearOfRelease(year,
                Sort.by(Sort.Direction.fromString(direction),
                        sorting));
        logger.debug("Returning {} movie(s) for year {}", movies.size(), year);
        return movies;
    }

//
//    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    public void addMovie(@RequestBody MovieInfoByGenre movieDTO) {
//        logger.info("putMovie new movie");
//        logger.debug("putMovie new movie {}", movieDTO);
//        repository.save(mapper.movieDTOToMovie(movieDTO));
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    public void editMovie(@RequestBody MovieInfoByGenre movieDTO,
//                          @PathVariable int id) {
//        logger.info("edit movie id {}", id);
//        logger.debug("edit movie {}", movieDTO);
//        repository.save(mapper.movieDTOToMovie(movieDTO));
//    }

    @Autowired
    public void setRepository(MovieRepository repository) {
        this.repository = repository;
    }

}

