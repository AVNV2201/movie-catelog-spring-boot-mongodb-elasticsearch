package dev.brainstorm.moviecatelogmongodb.models;

import dev.brainstorm.moviecatelogmongodb.models.enums.Gender;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("persons")
public class Person {

    @Id
    private String id;

    private String name;
    private Gender gender;

    public Person() {
    }

    public Person(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }
}
