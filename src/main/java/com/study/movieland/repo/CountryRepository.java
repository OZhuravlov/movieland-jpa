package com.study.movieland.repo;

import com.study.movieland.model.entity.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository<T extends Country, Integer> extends CrudRepository<T, Integer>{
}
