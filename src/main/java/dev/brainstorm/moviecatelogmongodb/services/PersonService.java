package dev.brainstorm.moviecatelogmongodb.services;

import dev.brainstorm.moviecatelogmongodb.models.Person;
import dev.brainstorm.moviecatelogmongodb.models.enums.Gender;

import java.util.List;

public interface PersonService {

    List<Person> getAllPersons(String name, Gender gender);
    Person getPersonById(String id);
    Person addPerson(Person person);
    Person updatePerson(String id, Person person);
    void deletePerson(String id);
}
