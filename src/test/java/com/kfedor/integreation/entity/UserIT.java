package com.kfedor.integreation.entity;

import com.kfedor.entity.User;
import com.kfedor.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserIT {

    @Test
    void ShouldPersistEntityAndGetEntity() {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
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
                .firstName("Alex")
                .lastName("Petrov")
                .email("petrov@gmail.com")
                .password("6789012")
                .build();
    }
}