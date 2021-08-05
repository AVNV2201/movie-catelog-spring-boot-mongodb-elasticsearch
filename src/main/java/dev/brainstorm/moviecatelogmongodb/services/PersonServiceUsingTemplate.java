package dev.brainstorm.moviecatelogmongodb.services;

import dev.brainstorm.moviecatelogmongodb.models.Person;
import dev.brainstorm.moviecatelogmongodb.models.enums.Gender;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("template")
public class PersonServiceUsingTemplate implements PersonService{

    private final MongoTemplate mongoTemplate;

    public PersonServiceUsingTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Person> getAllPersons(String name, Gender gender) {
        if(name == null && gender == null){
            return mongoTemplate.findAll(Person.class);
        }

        String nameRegex = "(.*)" + name + "(.*)";

        if(name != null && gender != null){
            Query query = new Query();
            query.addCriteria(Criteria.where("gender").is(gender)
                    .andOperator(Criteria.where("name").regex(nameRegex)));
            return mongoTemplate.find(query, Person.class);
        }

        if(gender != null){
            Query query = new Query();
            query.addCriteria(Criteria.where("gender").is(gender));
            return mongoTemplate.find(query, Person.class);
        }

        if(name != null){
            Query query = new Query();
            query.addCriteria(Criteria.where("name").regex(nameRegex));
            return mongoTemplate.find(query, Person.class);
        }
        return null;
    }

    @Override
    public Person getPersonById(String id) {
        return mongoTemplate.findById(id, Person.class);
    }

    @Override
    public Person addPerson(Person person) {
        return mongoTemplate.save(person);
    }

    @Override
    public Person updatePerson(String id, Person person) {
        Person _person = mongoTemplate.findById(id, Person.class);

        if(_person != null){
            _person.setName(person.getName());
            _person.setGender(person.getGender());
            return mongoTemplate.save(_person);
        }
        return null;
    }

    @Override
    public void deletePerson(String id) {
        Person person = mongoTemplate.findById(id, Person.class);
        if(person != null)
            mongoTemplate.remove(person);
    }
}
