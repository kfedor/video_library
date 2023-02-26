package com.kfedor.integreation.entity;

import com.kfedor.entity.Comment;
import com.kfedor.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentIT {

    @Test
    void ShouldPersistEntityAndGetEntity() {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Comment comment = getComment();
            session.persist(comment);
            session.getTransaction().commit();

            Comment actualResult = session.get(Comment.class, comment.getId());

            assertThat(actualResult.getId()).isEqualTo(comment.getId());
        }

    }

    private Comment getComment() {
        return Comment.builder()
                .filmId(2)
                .userId(2)
                .text("The first season was great, but the rest part of this serial hasn't impressed me")
                .rating(7.1)
                .build();
    }

}
