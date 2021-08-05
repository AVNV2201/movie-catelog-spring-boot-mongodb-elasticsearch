package dev.brainstorm.moviecatelogmongodb.services;

import dev.brainstorm.moviecatelogmongodb.models.Movie;
import dev.brainstorm.moviecatelogmongodb.models.enums.Genre;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("template")
public class MovieServiceUsingTemplate implements MovieService{
    @Override
    public Movie addMovie(Movie movie) {
        return null;
    }

    @Override
    public List<Movie> getAllMovies(Genre genre) {
        return null;
    }

    @Override
    public Movie getMovieById(String id) {
        return null;
    }

    @Override
    public List<Movie> searchMovies(String searchText) {
        return null;
    }

    @Override
    public Movie updateMovie(String id, Movie movie) {
        return null;
    }

    @Override
    public void deleteMovie(String id, boolean cascadeDeleteCrew) {

    }
}
