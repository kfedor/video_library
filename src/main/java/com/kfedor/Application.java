package com.kfedor;

import com.kfedor.entity.Film;
import com.kfedor.entity.FilmParticipant;
import com.kfedor.entity.Participant;
import com.kfedor.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.Instant;


public class Application {

    public static void main(String[] args) {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Film film = session.get(Film.class, 2L);
            Participant participant = session.get(Participant.class, 2L);

            FilmParticipant filmParticipant = FilmParticipant.builder()
                    .film(film)
                    .participant(participant)
                    .invitedAt(Instant.now())
                    .build();

            filmParticipant.setFilm(film);
            filmParticipant.setParticipant(participant);

            session.persist(filmParticipant);

            session.getTransaction().commit();


        }
    }

}
