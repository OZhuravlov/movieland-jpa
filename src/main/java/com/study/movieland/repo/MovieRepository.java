package com.study.movieland.repo;

import com.study.movieland.model.entity.Genre;
import com.study.movieland.model.entity.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository<T extends Movie, Integer> extends CrudRepository<T, Integer>
{

    T findByNameNative(String nameNative);

    T findByNameRussian(String nameRussian);

    List<T> findByYearOfRelease(Integer yearOfRelease);

    List<T> findByGenres(Genre genre);
}
