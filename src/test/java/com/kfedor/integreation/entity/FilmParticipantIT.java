package com.kfedor.integreation.entity;


import com.kfedor.entity.Film;
import com.kfedor.entity.FilmParticipant;
import com.kfedor.entity.Participant;
import com.kfedor.util.Genre;
import com.kfedor.util.HibernateTestUtil;
import com.kfedor.util.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class FilmParticipantIT {

    @Test
    void shouldDeleteEntity() {

        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Film film = getFilm();
            session.persist(film);
            Participant participant = getParticipant();
            session.persist(participant);
            FilmParticipant filmParticipant = getFilmParticipant(film, participant);
            session.persist(filmParticipant);
            session.flush();
            session.remove(filmParticipant);

            session.getTransaction().commit();

            FilmParticipant actualResult = session.get(FilmParticipant.class, filmParticipant.getId());

            assertThat(actualResult).isNull();
        }
    }

    @Test
    void shouldUpdateEntity() {

        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Film film = getFilm();
            session.persist(film);
            Participant participant = getParticipant();
            session.persist(participant);
            FilmParticipant filmParticipant = getFilmParticipant(film, participant);
            session.persist(filmParticipant);

            FilmParticipant initialFilmParticipant = session.get(FilmParticipant.class, filmParticipant.getId());
            initialFilmParticipant.setInvitedAt(Instant.parse("2022-06-21T19:34:50.63Z"));
            session.persist(initialFilmParticipant);

            session.getTransaction().commit();

            FilmParticipant actualResult = session.get(FilmParticipant.class, filmParticipant.getId());

            assertThat(actualResult.getInvitedAt()).isEqualTo(Instant.parse("2022-06-21T19:34:50.63Z"));


        }
    }

    @Test
    void shouldPersistEntityAndGetEntity() {

        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Film film = getFilm();
            session.persist(film);
            Participant participant = getParticipant();
            session.persist(participant);
            FilmParticipant filmParticipant = getFilmParticipant(film, participant);
            session.persist(filmParticipant);

            session.getTransaction().commit();

            FilmParticipant actualResult = session.get(FilmParticipant.class, filmParticipant.getId());

            assertThat(actualResult.getId()).isEqualTo(filmParticipant.getId());

        }
    }

    private FilmParticipant getFilmParticipant(Film film, Participant participant) {
        return FilmParticipant.builder()
                .film(film)
                .participant(participant)
                .invitedAt(Instant.now())
                .build();
    }

    private Film getFilm() {
        return Film.builder()
                .title("testFilm")
                .productionCompany("testProdCompany")
                .releaseYear(2020)
                .genre(Genre.ACTION)
                .productionCountry("testProdCountry")
                .build();
    }

    private Participant getParticipant() {
        return Participant.builder()
                .firstName("Leo")
                .lastName("DiCaprio")
                .role(Role.ACTOR)
                .build();
    }

}