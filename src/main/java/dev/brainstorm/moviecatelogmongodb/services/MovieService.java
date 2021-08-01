package dev.brainstorm.moviecatelogmongodb.services;

import dev.brainstorm.moviecatelogmongodb.esrepositories.MovieRepository;
import dev.brainstorm.moviecatelogmongodb.models.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies(String name){
        List<Movie> movies = new ArrayList<>();

        if(name == null){
            movieRepository.findAll().forEach(movies::add);
        }
        else{
            movieRepository.findByName(name).forEach(movies::add);
        }
        return movies;
    }
}
