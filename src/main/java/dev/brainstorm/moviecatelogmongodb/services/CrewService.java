package dev.brainstorm.moviecatelogmongodb.services;

import dev.brainstorm.moviecatelogmongodb.models.Crew;
import dev.brainstorm.moviecatelogmongodb.models.enums.Role;

import java.util.List;

public interface CrewService {

    List<Crew> getAllCrew(String movieId, String personId);

    List<Crew> getCrewByRole(Role role);

    Crew getCrewById(String id);

    Crew addCrew(Crew crew);

    Crew updateCrew(String id, Crew crew);

    void deleteCrew(String id);

    void deleteCrewByMovieId(String movieId);
}
