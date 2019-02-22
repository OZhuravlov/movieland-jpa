package com.study.movieland.dto;

import com.study.movieland.model.entity.Country;

import java.util.List;

public interface MovieInfoByGenre {
    int getId();

    String getNameNative();

    String getNameRussian();

    int getYearOfRelease();

    double getRating();

    String getPicturePath();

    double getPrice();

    String getDescription();

    List<Country> getCountries();

}
