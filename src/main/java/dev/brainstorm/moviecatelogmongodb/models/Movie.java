package dev.brainstorm.moviecatelogmongodb.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@Document(indexName = "movies")
public class Movie {

    @Id
    String id;

    @Field(type = FieldType.Text, name = "name")
    String name;

    @Field(type = FieldType.Text, name = "desc")
    String description;

    public Movie(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
