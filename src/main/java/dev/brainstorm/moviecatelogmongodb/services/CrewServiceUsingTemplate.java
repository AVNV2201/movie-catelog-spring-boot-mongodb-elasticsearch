package dev.brainstorm.moviecatelogmongodb.services;

import dev.brainstorm.moviecatelogmongodb.config.RabbitMQConfig;
import dev.brainstorm.moviecatelogmongodb.models.Crew;
import dev.brainstorm.moviecatelogmongodb.models.enums.Role;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("template")
@Service
public class CrewServiceUsingTemplate implements CrewService{

    private final MongoTemplate mongoTemplate;

    public CrewServiceUsingTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Crew> getAllCrew(String movieId, String personId) {
        Query query = new Query();
        if(movieId != null){
            query.addCriteria(Criteria.where("movieId").is(movieId));
        }

        if(personId != null){
            query.addCriteria(Criteria.where("personId").is(personId));
        }

        return mongoTemplate.find(query, Crew.class);
    }

    @Override
    public List<Crew> getCrewByRole(Role role) {
        Query query = new Query();
        query.addCriteria(Criteria.where("role").is(role));
        return mongoTemplate.find(query, Crew.class);
    }

    @Override
    public Crew getCrewById(String id) {
        return mongoTemplate.findById(id, Crew.class);
    }

    @Override
    public Crew addCrew(Crew crew) {
        return mongoTemplate.save(crew);
    }

    @Override
    public Crew updateCrew(String id, Crew crew) {
        Crew _crew = mongoTemplate.findById(id, Crew.class);

        if(_crew != null){
            _crew.setMovieId(crew.getMovieId());
            _crew.setPersonId(crew.getPersonId());
            _crew.setRole(crew.getRole());
            _crew.setCharacterName(crew.getCharacterName());
            return mongoTemplate.save(_crew);
        }

        return null;
    }

    @Override
    public void deleteCrew(String id) {
        Crew crew = mongoTemplate.findById(id, Crew.class);
        if(crew != null)
            mongoTemplate.remove(crew);
    }

    @Override
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void deleteCrewByMovieId(String movieId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("movieId").is(movieId));
        mongoTemplate.findAllAndRemove(query, Crew.class);
    }
}



