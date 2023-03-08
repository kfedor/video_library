package com.kfedor.integreation.entity;

import com.kfedor.entity.Comment;
import com.kfedor.entity.Film;
import com.kfedor.entity.User;
import com.kfedor.util.Genre;
import com.kfedor.util.HibernateTestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentIT {
    @Test
    void shouldDeleteEntity() {

        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Film film = getFilm();
            session.persist(film);
            User user = getUser();
            session.persist(user);
            Comment comment = getComment(film, user);
            session.persist(comment);
            session.flush();
            session.remove(comment);

            session.getTransaction().commit();

            Comment actualResult = session.get(Comment.class, comment.getId());

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
            User user = getUser();
            session.persist(user);
            Comment comment = getComment(film, user);
            session.persist(comment);

            Comment initialComment = session.get(Comment.class, comment.getId());
            initialComment.setRating(8.8);
            session.persist(initialComment);

            session.getTransaction().commit();

            Comment actualResult = session.get(Comment.class, initialComment.getId());

            assertThat(actualResult.getRating()).isEqualTo(8.8);
        }
    }

    @Test
    void shouldPersistEntityAndGetEntity() {

        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Film film = getFilm();
            session.persist(film);
            User user = getUser();
            session.persist(user);
            Comment comment = getComment(film, user);
            session.persist(comment);
            session.getTransaction().commit();

            Comment actualResult = session.get(Comment.class, comment.getId());

            assertThat(actualResult.getId()).isEqualTo(comment.getId());
        }

    }

    private Comment getComment(Film film, User user) {
        return Comment.builder()
                .film(film)
                .user(user)
                .text("test comment")
                .rating(7.1)
                .build();
    }

    private User getUser() {
        return User.builder()
                .firstName("test")
                .lastName("testov")
                .email("test@test.com")
                .password("test")
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

}
