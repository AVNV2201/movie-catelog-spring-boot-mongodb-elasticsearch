package dev.brainstorm.moviecatelogmongodb.services;

import dev.brainstorm.moviecatelogmongodb.config.RabbitMQConfig;
import dev.brainstorm.moviecatelogmongodb.models.Crew;
import dev.brainstorm.moviecatelogmongodb.models.enums.Role;
import dev.brainstorm.moviecatelogmongodb.mongorepositories.CrewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Profile("repo")
public class CrewServiceUsingRepository implements CrewService {

    private final CrewRepository crewRepository;

    public CrewServiceUsingRepository(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    @Override
    public List<Crew> getAllCrew(String movieId, String personId){
        if(movieId != null){
            return crewRepository.findByMovieId(movieId);
        }
        if(personId != null){
            return crewRepository.findByPersonId(personId);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Crew> getCrewByRole(Role role){
        return role == null ? null : crewRepository.findByRole(role);
    }

    @Override
    public Crew getCrewById(String id){
        Optional<Crew> crew = crewRepository.findById(id);
        return crew.orElse(null);
    }

    @Override
    public Crew addCrew(Crew crew){
        return crewRepository.save(crew);
    }

    @Override
    public Crew updateCrew(String id, Crew crew){
        Optional<Crew> crewData = crewRepository.findById(id);
        if(crewData.isPresent()){
            Crew _crew = crewData.get();
            _crew.setMovieId(crew.getMovieId());
            _crew.setPersonId(crew.getPersonId());
            _crew.setRole(crew.getRole());
            _crew.setCharacterName(crew.getCharacterName());
            return crewRepository.save(_crew);
        }
        return null;
    }

    @Override
    public void deleteCrew(String id){
        crewRepository.deleteById(id);
    }

    @Override
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void deleteCrewByMovieId(String movieId){
        List<Crew> crew = crewRepository.findByMovieId(movieId);
        crewRepository.deleteAll(crew);
        log.info("All crew are deleted with movieId: {}", movieId);
    }
}
