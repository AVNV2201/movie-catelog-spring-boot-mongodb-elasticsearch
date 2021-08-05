package dev.brainstorm.moviecatelogmongodb.services;

import dev.brainstorm.moviecatelogmongodb.models.Movie;
import dev.brainstorm.moviecatelogmongodb.models.enums.Genre;

import java.util.List;

public interface MovieService {

    Movie addMovie(Movie movie);

    List<Movie> getAllMovies(Genre genre);

    Movie getMovieById(String id);

    List<Movie> searchMovies(String searchText);

    Movie updateMovie(String id, Movie movie);

    void deleteMovie(String id, boolean cascadeDeleteCrew);
}
