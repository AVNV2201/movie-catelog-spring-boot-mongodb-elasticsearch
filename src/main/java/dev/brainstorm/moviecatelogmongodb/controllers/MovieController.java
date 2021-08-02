package dev.brainstorm.moviecatelogmongodb.controllers;

import dev.brainstorm.moviecatelogmongodb.models.Movie;
import dev.brainstorm.moviecatelogmongodb.models.enums.Genre;
import dev.brainstorm.moviecatelogmongodb.services.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@Slf4j
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie")
    public ResponseEntity<List<Movie>> getAllMovies(@PathParam("genre") Genre genre){
        try {
            List<Movie> movies = movieService.getAllMovies(genre);
            if(movies.isEmpty()){
                log.info("No movies found");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            log.info("{} movies found", movies.size());
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movie/search")
    public ResponseEntity<List<Movie>> searchMovies(@PathParam("q") String q){
        try {
            List<Movie> movies = movieService.searchMovies(q);
            if(movies.isEmpty()){
                log.info("No movies found");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            log.info("{} movies found", movies.size());
            return new ResponseEntity<>(movies, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") String id){
        try {
            Movie movie = movieService.getMovieById(id);
            if(movie == null){
                log.error("Movie not found with id: {}", id);
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/movie")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        try {
            Movie _movie = movieService.addMovie(movie);
            if(_movie == null){
                log.error("Unable to save {}", movie);
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(_movie, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/movie/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") String id, @RequestBody Movie movie){
        try {
            Movie _movie = movieService.updateMovie(id, movie);

            if(_movie == null){
                log.error("Unable to update Movie with id: {}", id);
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            log.info("Updated Movie with id: {}", id);
            return new ResponseEntity<>(_movie, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") String id,@PathParam("cascade") boolean cascade){
        try {
            movieService.deleteMovie(id, cascade);
            log.info("Movie deleted with id: {}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
