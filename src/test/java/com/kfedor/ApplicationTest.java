package com.kfedor;

import com.kfedor.entity.User;
import com.kfedor.util.HibernateTestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

public class ApplicationTest {

    @Test
    void checkDocker() {

        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
                Session session = sessionFactory.openSession()) {
                session.beginTransaction();

            User user = User.builder()
                    .firstName("test")
                    .lastName("testov")
                    .email("testovich@testov.com")
                    .password("test")
                    .build();

            session.persist(user);

            session.getTransaction().commit();
        }

    }

}
