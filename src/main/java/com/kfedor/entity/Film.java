package com.kfedor.entity;

import com.kfedor.util.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"comments", "filmParticipants"})
@ToString(exclude = {"comments", "filmParticipants"})
@Builder
@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "production_company")
    private String productionCompany;

    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    @Column(name = "production_country", nullable = false)
    private String productionCountry;

    @OneToMany(mappedBy = "film")
    private List<Comment> comments;

    @Builder.Default
    @OneToMany(mappedBy = "film")
    private List<FilmParticipant> filmParticipants = new ArrayList<>();

}
