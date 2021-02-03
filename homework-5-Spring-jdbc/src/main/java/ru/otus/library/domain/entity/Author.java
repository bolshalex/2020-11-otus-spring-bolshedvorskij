package ru.otus.library.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private Long id;
    private String name;

    public Author(Long id) {
        this.id = id;
    }
}
