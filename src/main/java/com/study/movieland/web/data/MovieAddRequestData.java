package com.study.movieland.web.data;

import lombok.Data;

import java.util.Set;

@Data
public class MovieAddRequestData {
    private String nameNative;
    private String nameRussian;
    private int yearOfRelease;
    private String description;
    private Double price;
    private String picturePath;
    private Set<Integer> countries;
    private Set<Integer> genres;

}
