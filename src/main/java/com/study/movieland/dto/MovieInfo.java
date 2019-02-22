package com.study.movieland.dto;

import com.study.movieland.model.entity.Country;
import com.study.movieland.model.entity.Genre;

import java.util.List;

public interface MovieInfo {
    int getId();

    String getNameNative();

    String getNameRussian();

    int getYearOfRelease();

    double getRating();

    String getPicturePath();

    double getPrice();

    String getDescription();

    List<Country> getCountries();

    List<Genre> getGenres();
}
