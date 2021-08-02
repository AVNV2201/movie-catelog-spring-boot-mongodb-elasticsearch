package dev.brainstorm.moviecatelogmongodb.controllers;

import dev.brainstorm.moviecatelogmongodb.models.Crew;
import dev.brainstorm.moviecatelogmongodb.models.Person;
import dev.brainstorm.moviecatelogmongodb.models.enums.Gender;
import dev.brainstorm.moviecatelogmongodb.services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@Slf4j
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getPersons(@PathParam("name") String name, @PathParam("gender") Gender gender){
        try {
            List<Person> persons = personService.getAllPersons(name, gender);

            if(persons.isEmpty()){
                log.info("No persons found");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            log.info("{} persons returned", persons.size());
            return new ResponseEntity<>(persons, HttpStatus.OK);
        }
        catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person person){
        try {
            Person _person = personService.addPerson(person);
            log.info("Person created: {}", person);
            return new ResponseEntity<>(_person, HttpStatus.CREATED);
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") String id){
        try {
            Person person = personService.getPersonById(id);
            if(person == null){
                log.error("Person not found with id: {}", id);
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(person, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") String id, @RequestBody Person person){
        try {
            Person _person = personService.updatePerson(id, person);

            if(_person == null){
                log.error("Unable to update Person with id: {}", id);
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            log.info("Updated Person with id: {}", id);
            return new ResponseEntity<>(_person, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") String id){
        try {
            personService.deletePerson(id);
            log.info("Person deleted with id: {}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
