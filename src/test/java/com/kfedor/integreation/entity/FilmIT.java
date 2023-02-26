package com.kfedor.integreation.entity;

import com.kfedor.entity.Film;
import com.kfedor.util.Genre;
import com.kfedor.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FilmIT {

    @Test
    void ShouldPersistEntityAndGetEntity() {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Film film = getFilm();
            session.persist(film);
            session.getTransaction().commit();

            Film actualResult = session.get(Film.class, film.getId());

            assertThat(actualResult.getId()).isEqualTo(film.getId());
        }

    }

    private Film getFilm() {
        return Film.builder()
                .title("TestFilm")
                .productionCompany("TestCompany")
                .releaseYear(2020)
                .genre(Genre.ACTION)
                .productionCountry("TestCountry")
                .build();
    }

}
