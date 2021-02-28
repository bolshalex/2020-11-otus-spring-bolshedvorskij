package ru.otus.library.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedEntityGraph(name = "genres-entity-graph",
        attributeNodes = {@NamedAttributeNode("genres")})
@NamedEntityGraph(name = "authors-entity-graph",
        attributeNodes = {@NamedAttributeNode("authors")})
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;

    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    @ManyToMany(targetEntity = Author.class, fetch = FetchType.EAGER)
    @JoinTable(name = "author_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;


    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    @ManyToMany(targetEntity = Genre.class)
    @JoinTable(name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
    @OneToMany(targetEntity = BookComment.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private List<BookComment> comments;
}
