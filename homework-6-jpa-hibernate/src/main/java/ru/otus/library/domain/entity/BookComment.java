package ru.otus.library.domain.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "book_comments")
public class BookComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Book.class)
    @JoinColumn(name = "book_id", updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Book book;
}
