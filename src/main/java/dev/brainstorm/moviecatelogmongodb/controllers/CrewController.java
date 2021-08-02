package dev.brainstorm.moviecatelogmongodb.controllers;

import dev.brainstorm.moviecatelogmongodb.models.Crew;
import dev.brainstorm.moviecatelogmongodb.models.enums.Role;
import dev.brainstorm.moviecatelogmongodb.services.CrewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@Slf4j
public class CrewController {

    private final CrewService crewService;

    public CrewController(CrewService crewService) {
        this.crewService = crewService;
    }

    @GetMapping("/crew")
    public ResponseEntity<List<Crew>> getCrewList(@PathParam("movieId") String movieId, @PathParam("personId") String personId){
        try {
            if((movieId == null && personId == null) || (movieId != null && personId != null)){
                log.warn("Crew not found due to bad request");
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            List<Crew> crew;
            if(movieId != null){
                crew = crewService.getAllCrew(movieId, null);
            }
            else{
                crew = crewService.getAllCrew(null, personId);
            }

            if(crew ==null || crew.isEmpty()){
                log.info("No Crew Found");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            log.info("{} crew found", crew.size());
            return new ResponseEntity<>(crew, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/crew/{role}")
    public ResponseEntity<List<Crew>> getCrewByRole(@PathVariable("role") Role role){
        try {
            if(role == null){
                log.warn("Crew not found due to bad request");
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            List<Crew> crew = crewService.getCrewByRole(role);

            if(crew.isEmpty()){
                log.info("No Crew Found with role: {}", role);
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            log.info("{} crew found with role: {}", crew.size(), role);
            return new ResponseEntity<>(crew, HttpStatus.OK);
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/crew")
    public ResponseEntity<Crew> addCrew(@RequestBody Crew crew){
        try {
            if(crew == null) {
                log.warn("Crew not created due to bad request");
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            Crew _crew = crewService.addCrew(crew);
            log.info("crew created successfully: {}", _crew);
            return new ResponseEntity<>(_crew, HttpStatus.CREATED);
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
