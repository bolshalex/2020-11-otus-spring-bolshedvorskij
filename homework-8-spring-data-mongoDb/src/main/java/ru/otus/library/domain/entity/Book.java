package ru.otus.library.domain.entity;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class Book {
    @SerializedName("_id")
    @Id
    private String id;

    @Field(name = "title")
    private String title;

    @Field(name = "authors")
    @DBRef
    private List<Author> authors;

    @DBRef
    private List<Genre> genres;
}
