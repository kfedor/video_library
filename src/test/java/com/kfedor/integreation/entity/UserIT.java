package com.kfedor.integreation.entity;

import com.kfedor.entity.User;
import com.kfedor.util.HibernateTestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserIT {

    @Test
    void shouldDeleteEntity() {

        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = getUser();
            session.persist(user);
            session.flush();
            session.remove(user);

            session.getTransaction().commit();

            User actualResult = session.get(User.class, user.getId());

            assertThat(actualResult).isNull();

        }
    }

    @Test
    void shouldUpdateEntity() {
        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = getUser();
            session.persist(user);

            User initialUser = session.get(User.class, user.getId());
            initialUser.setFirstName("changedName");
            session.persist(initialUser);

            session.getTransaction().commit();

            User actualResult = session.get(User.class, initialUser.getId());

            assertThat(actualResult.getFirstName()).isEqualTo("changedName");
        }

    }

    @Test
    void shouldPersistEntityAndGetEntity() {

        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = getUser();
            session.persist(user);
            session.getTransaction().commit();

            User actualResult = session.get(User.class, user.getId());

            assertThat(actualResult.getId()).isEqualTo(user.getId());
        }

    }

    private User getUser() {
        return User.builder()
                .firstName("Alex1")
                .lastName("Petrov1")
                .email("petrov1@gmail.com")
                .password("6789012")
                .build();
    }
}