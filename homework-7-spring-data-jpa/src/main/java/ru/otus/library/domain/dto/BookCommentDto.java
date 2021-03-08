package ru.otus.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCommentDto {
    private Long bookId;
    private Long commentId;
    private String text;
}
