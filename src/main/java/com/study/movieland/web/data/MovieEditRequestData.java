package com.study.movieland.web.data;

import lombok.Data;

import java.util.Set;

@Data
public class MovieEditRequestData {
    private String nameNative;
    private String nameRussian;
    private String picturePath;
    private Set<Integer> countries;
    private Set<Integer> genres;
}
