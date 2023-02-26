package com.kfedor.integreation.entity;


import com.kfedor.entity.FilmParticipant;
import com.kfedor.util.HibernateUtil;
import com.kfedor.util.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FilmParticipantIT {

    @Test
    void ShouldPersistEntityAndGetEntity() {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            FilmParticipant filmParticipant = getFilmParticipant();
            session.persist(filmParticipant);
            session.getTransaction().commit();

            FilmParticipant actualResult = session.get(FilmParticipant.class, filmParticipant.getId());

            assertThat(actualResult.getId()).isEqualTo(filmParticipant.getId());
        }

    }

    private FilmParticipant getFilmParticipant() {
        return FilmParticipant.builder()
                .firstName("Leo")
                .lastName("DiCaprio")
                .role(Role.ACTOR)
                .build();
    }

}