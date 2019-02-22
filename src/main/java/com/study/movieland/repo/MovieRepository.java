package com.study.movieland.repo;

import com.study.movieland.dto.MovieInfo;
import com.study.movieland.dto.MovieInfoByCountry;
import com.study.movieland.dto.MovieInfoByGenre;
import com.study.movieland.dto.MovieInfoFindAll;
import com.study.movieland.model.entity.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    List<MovieInfoFindAll> findAllProjectedBy(Sort sort);

    Optional<MovieInfo> getById(Integer integer);

    @EntityGraph(value = "Movie.detail")
    List<MovieInfo> findByNameNativeLikeIgnoreCase(String searchTerm);

    @EntityGraph(value = "Movie.detail")
    List<MovieInfo> findByNameRussianLikeIgnoreCase(String searchTerm);

    @EntityGraph(value = "Movie.detail")
    List<MovieInfo> findByYearOfRelease(Integer yearOfRelease, Sort sort);

    @EntityGraph(value = "Movie.detail")
    List<MovieInfoByCountry> findByCountries_Id(Integer id, Sort sort);

    @EntityGraph(value = "Movie.detail")
    List<MovieInfoByGenre> findByGenres_Id(Integer id, Sort sort);
}
