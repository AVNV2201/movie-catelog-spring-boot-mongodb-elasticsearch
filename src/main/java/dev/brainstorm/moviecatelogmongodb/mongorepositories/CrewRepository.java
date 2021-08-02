package dev.brainstorm.moviecatelogmongodb.mongorepositories;

import dev.brainstorm.moviecatelogmongodb.models.Crew;
import dev.brainstorm.moviecatelogmongodb.models.enums.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CrewRepository extends MongoRepository<Crew, String> {
    List<Crew> findByMovieId(String movieId);
    List<Crew> findByPersonId(String personId);
    List<Crew> findByRole(Role role);
}
