package dev.brainstorm.moviecatelogmongodb.services;

import dev.brainstorm.moviecatelogmongodb.config.RabbitMQConfig;
import dev.brainstorm.moviecatelogmongodb.esrepositories.MovieRepository;
import dev.brainstorm.moviecatelogmongodb.models.Movie;
import dev.brainstorm.moviecatelogmongodb.models.enums.Genre;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Profile("repo")
public class MovieServiceUsingRepository implements MovieService{

    private final MovieRepository movieRepository;

    private final RabbitTemplate rabbitTemplate;

    public MovieServiceUsingRepository(MovieRepository movieRepository, RabbitTemplate rabbitTemplate) {
        this.movieRepository = movieRepository;
        this.rabbitTemplate = rabbitTemplate;
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

    public Movie getMovieById(String id){
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.orElse(null);
    }

    public List<Movie> searchMovies(String searchText){
        Set<Movie> movies = new HashSet<>();
        movies.addAll(movieRepository.findByNameContaining(searchText));
        movies.addAll(movieRepository.findByDescriptionContaining(searchText));
        return new ArrayList<>(movies);
    }

    public Movie updateMovie(String id, Movie movie){
        Optional<Movie> movieData = movieRepository.findById(id);

        if(movieData.isPresent()){
            Movie _movie = movieData.get();
            _movie.setName(movie.getName());
            _movie.setDescription(movie.getDescription());
            _movie.setGenre(movie.getGenre());
            _movie.setReleaseDate(movie.getReleaseDate());

            return movieRepository.save(_movie);
        }
        return null;
    }

    public void deleteMovie(String id, boolean cascadeDeleteCrew){
        if(cascadeDeleteCrew){
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, id);
        }
        movieRepository.deleteById(id);
    }
}
