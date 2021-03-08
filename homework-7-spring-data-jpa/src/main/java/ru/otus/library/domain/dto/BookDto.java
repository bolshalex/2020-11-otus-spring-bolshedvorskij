package ru.otus.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long bookId;
    private String bookTitle;
    private List<AuthorDto> authors;
    private List<GenreDto> genres;
}
