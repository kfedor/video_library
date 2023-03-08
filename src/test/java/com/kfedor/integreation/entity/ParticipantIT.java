package com.kfedor.integreation.entity;


import com.kfedor.entity.Participant;
import com.kfedor.util.HibernateTestUtil;
import com.kfedor.util.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantIT {

    @Test
    void shouldDeleteEntity() {

        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Participant participant = getParticipant();
            session.persist(participant);
            session.flush();
            session.remove(participant);

            session.getTransaction().commit();

            Participant actualResult = session.get(Participant.class, participant.getId());

            assertThat(actualResult).isNull();

        }
    }

    @Test
    void shouldUpdateEntity() {

        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Participant participant = getParticipant();
            session.persist(participant);

            Participant initialParticipant = session.get(Participant.class, participant.getId());
            initialParticipant.setFirstName("changedName");
            session.persist(initialParticipant);

            session.getTransaction().commit();

            Participant actualResult = session.get(Participant.class, initialParticipant.getId());

            assertThat(actualResult.getFirstName()).isEqualTo("changedName");
        }

    }

    @Test
    void shouldPersistEntityAndGetEntity() {

        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Participant participant = getParticipant();
            session.persist(participant);
            session.getTransaction().commit();

            Participant actualResult = session.get(Participant.class, participant.getId());

            assertThat(actualResult.getId()).isEqualTo(participant.getId());
        }

    }

    private Participant getParticipant() {
        return Participant.builder()
                .firstName("Leo")
                .lastName("DiCaprio")
                .role(Role.ACTOR)
                .build();
    }

}