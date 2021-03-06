package ru.otus.library.repository.genre;

import org.springframework.stereotype.Repository;
import ru.otus.library.domain.entity.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JpaGenreRepository implements GenreRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Genre createGenre(Genre genre) {
        em.persist(genre);
        em.flush();
        return genre;
    }

    @Override
    public void updateGenre(Genre genre) {
        em.merge(genre);
    }

    @Override
    public void deleteGenre(Long id) {
        Query query =
                em.createQuery("delete from Genre g where g.id=:genreId")
                        .setParameter("genreId", id);
        query.executeUpdate();
    }

    @Override
    public Genre getById(Long id) {
        return em.find(Genre.class, id);
    }


    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }
}
