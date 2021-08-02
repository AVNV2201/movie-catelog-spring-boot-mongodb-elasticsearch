package dev.brainstorm.moviecatelogmongodb.models;

import dev.brainstorm.moviecatelogmongodb.models.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
@Document("crews")
public class Crew {

    @Id
    private String id;
    private String personId;
    private String movieId;
    private Role role;
    private String characterName;

    public Crew(String personId, String movieId, Role role, String characterName) {
        this.personId = personId;
        this.movieId = movieId;
        this.role = role;
        this.characterName = characterName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Crew crew = (Crew) o;

        return id != null ? id.equals(crew.id) : crew.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
