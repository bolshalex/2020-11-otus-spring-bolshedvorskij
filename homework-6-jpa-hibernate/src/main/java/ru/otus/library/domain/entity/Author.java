package ru.otus.library.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "books")
@EqualsAndHashCode(exclude = "books")
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(targetEntity = Book.class, fetch = FetchType.LAZY)
    @JoinTable(name = "author_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
