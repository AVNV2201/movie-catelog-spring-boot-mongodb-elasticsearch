package dev.brainstorm.moviecatelogmongodb.services;

import dev.brainstorm.moviecatelogmongodb.esrepositories.MovieRepository;
import dev.brainstorm.moviecatelogmongodb.models.Movie;
import dev.brainstorm.moviecatelogmongodb.models.enums.Genre;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies(Genre genre){
        if(genre != null){
            return movieRepository.findByGenre(genre);
        }
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        return movies;
    }

    public List<Movie> searchMovies(String searchText){
        Set<Movie> movies = new HashSet<>();
        movies.addAll(movieRepository.findByNameContaining(searchText));
        movies.addAll(movieRepository.findByDescriptionContaining(searchText));
        return new ArrayList<>(movies);
    }
}
