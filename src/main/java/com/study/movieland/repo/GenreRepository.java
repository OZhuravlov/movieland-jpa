package com.study.movieland.repo;

import com.study.movieland.model.entity.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository<T extends Genre, Integer> extends CrudRepository<T, Integer> {
}
