package dev.brainstorm.moviecatelogmongodb.services;

import dev.brainstorm.moviecatelogmongodb.models.Person;
import dev.brainstorm.moviecatelogmongodb.models.enums.Gender;
import dev.brainstorm.moviecatelogmongodb.mongorepositories.PersonRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("repo")
public class PersonServiceUsingRepository implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceUsingRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAllPersons(String name, Gender gender) {
        if(name == null && gender == null){
            return personRepository.findAll();
        }

        List<Person> persons = new ArrayList<>();

        if(gender != null){
            persons.addAll(personRepository.findByGender(gender));
        }

        if(name != null){
            if(gender == null)
                persons.addAll(personRepository.findByNameContaining(name));
            else{
                List<Person> results = new ArrayList<>();
                for(Person person: persons){
                    if( person.getName().matches("(.*)"+name+"(.*)") )
                        results.add(person);
                }
                return results;
            }
        }

        return persons;
    }

    @Override
    public Person getPersonById(String id) {
        Optional<Person> person = personRepository.findById(id);

        return person.orElse(null);
    }

    @Override
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(String id, Person person) {
        Optional<Person> personOptional = personRepository.findById(id);

        if(personOptional.isPresent()){
            Person _person = personOptional.get();
            _person.setName(person.getName());
            _person.setGender(person.getGender());
            return personRepository.save(_person);
        }

        return null;
    }

    @Override
    public void deletePerson(String id) {
        personRepository.deleteById(id);
    }
}
