package dev.brainstorm.moviecatelogmongodb.controllers;

import dev.brainstorm.moviecatelogmongodb.models.Movie;
import dev.brainstorm.moviecatelogmongodb.models.enums.Genre;
import dev.brainstorm.moviecatelogmongodb.services.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@Api(value = "My controller for Movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/movie",produces = "application/json")
    @ApiOperation(value = "Get list of movies of a genre or get all movies")
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

    @GetMapping(value = "/movie/search", produces = "application/json")
    @ApiOperation(value = "Search for a movie")
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

    @GetMapping(value = "/movie/{id}", produces = "application/json")
    @ApiOperation(value = "Get a Movie by id", response = Movie.class)
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

    @PostMapping(value = "/movie", produces = "application/json")
    @ApiOperation(value = "Add a new movie", response = Movie.class)
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

    @PatchMapping(value = "/movie/{id}", produces = "application/json")
    @ApiOperation(value = "Update info of a movie", response = Movie.class)
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
    @ApiOperation(value = "Delete a movie by its id")
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
