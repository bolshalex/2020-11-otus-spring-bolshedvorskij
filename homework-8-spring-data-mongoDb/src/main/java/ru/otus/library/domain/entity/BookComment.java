package ru.otus.library.domain.entity;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookComment {
    @SerializedName("_id")
    @Id
    private String id;

    @Field(name = "text")
    private String text;

    @DBRef
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Book book;
}
