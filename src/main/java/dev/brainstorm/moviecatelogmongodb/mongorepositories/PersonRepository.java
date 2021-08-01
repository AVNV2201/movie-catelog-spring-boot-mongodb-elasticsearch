package dev.brainstorm.moviecatelogmongodb.mongorepositories;

import dev.brainstorm.moviecatelogmongodb.models.Person;
import dev.brainstorm.moviecatelogmongodb.models.enums.Gender;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, String> {

    List<Person> findByNameContaining(String name);
    List<Person> findByGender(Gender gender);
}
