package ru.otus.library.domain.entity;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "authors")
public class Author {
    @SerializedName("_id")
    @Id
    private String id;

    @Field(name = "name")
    private String name;

    public Author(String authorId) {
        id = authorId;
    }
}
