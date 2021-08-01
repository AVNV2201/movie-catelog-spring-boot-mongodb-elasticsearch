package dev.brainstorm.moviecatelogmongodb.esrepositories;

import dev.brainstorm.moviecatelogmongodb.models.Movie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MovieRepository extends ElasticsearchRepository<Movie, String> {
    List<Movie> findByName(String name);
}
