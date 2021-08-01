package dev.brainstorm.moviecatelogmongodb.models;

import dev.brainstorm.moviecatelogmongodb.models.enums.Genre;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Document(indexName = "movies")
public class Movie {

    @Id
    private String id;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Text, name = "desc")
    private String description;

    @Field(type = FieldType.Keyword)
    private Genre genre;

    @Field(type = FieldType.Date)
    private Date releaseDate;

    public Movie(String name, String description, Genre genre, Date releaseDate) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return id != null ? id.equals(movie.id) : movie.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
