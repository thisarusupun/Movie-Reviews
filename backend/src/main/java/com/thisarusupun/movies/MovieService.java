package com.thisarusupun.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // all movies
    public List<Movie> allMovies(){
        return movieRepository.findAll();
    }

    // movie by imdb id
    public Optional<Movie> singleMovie(String id){
        return movieRepository.findMovieByImdbId(id);
    }
}
