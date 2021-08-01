package dev.brainstorm.moviecatelogmongodb.esrepositories;

import dev.brainstorm.moviecatelogmongodb.models.Movie;
import dev.brainstorm.moviecatelogmongodb.models.enums.Genre;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MovieRepository extends ElasticsearchRepository<Movie, String> {
    List<Movie> findByNameContaining(String name);
    List<Movie> findByDescriptionContaining(String description);
    List<Movie> findByGenre(Genre genre);
}
