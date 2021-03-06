package com.study.movieland;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.study.movieland.repo")
public class MovielandApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovielandApplication.class, args);
    }

}

