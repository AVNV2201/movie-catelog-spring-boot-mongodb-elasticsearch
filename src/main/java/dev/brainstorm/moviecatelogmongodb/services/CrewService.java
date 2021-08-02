package dev.brainstorm.moviecatelogmongodb.services;

import dev.brainstorm.moviecatelogmongodb.models.Crew;
import dev.brainstorm.moviecatelogmongodb.models.enums.Role;
import dev.brainstorm.moviecatelogmongodb.mongorepositories.CrewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CrewService {

    private final CrewRepository crewRepository;

    public CrewService(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    public List<Crew> getAllCrew(String movieId, String personId){
        if(movieId != null){
            return crewRepository.findByMovieId(movieId);
        }
        if(personId != null){
            return crewRepository.findByPersonId(personId);
        }
        return new ArrayList<>();
    }

    public List<Crew> getCrewByRole(Role role){
        return role == null ? null : crewRepository.findByRole(role);
    }

    public Crew getCrewById(String id){
        Optional<Crew> crew = crewRepository.findById(id);
        return crew.orElse(null);
    }

    public Crew addCrew(Crew crew){
        return crewRepository.save(crew);
    }
}
