package com.kfedor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@NoArgsConstructor
@Builder
@Entity
@Table(name = "film_participant", schema = "public")
public class FilmParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne(optional = false)
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @Column(name = "invited_at", nullable = false)
    private Instant invitedAt;

    public FilmParticipant(Long id, Film film, Participant participant, Instant invitedAt) {
        this.id = id;
        this.film = film;
        this.film.getFilmParticipants().add(this);
        this.participant = participant;
        this.participant.getFilmParticipants().add(this);
        this.invitedAt = invitedAt;
    }

}
