CREATE TABLE IF NOT EXISTS users
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(128)        NOT NULL,
    last_name  VARCHAR(128)        NOT NULL,
    email      VARCHAR(128) UNIQUE NOT NULL,
    password   VARCHAR(128)        NOT NULL
);

INSERT INTO users (first_name, last_name, email, password)
VALUES ('Ivan', 'Ivanov', 'ivanov@gmail.com', '12345'),
       ('Irina', 'Ivanova', 'irina@gmail.com', '54321');

CREATE TABLE IF NOT EXISTS film
(
    id                 BIGSERIAL PRIMARY KEY,
    title              VARCHAR(128) NOT NULL,
    production_company VARCHAR(128),
    release_year       INT          NOT NULL,
    genre              VARCHAR(32)  NOT NULL,
    production_country VARCHAR(128) NOT NULL
);

INSERT INTO film (title, production_company,release_year, genre, production_country)
VALUES ('Taxi Driver', 'SBS', '2021', 'THRILLER', 'South Korea'),
       ('Penthouse', 'SBS', '2021', 'DRAMA', 'South Korea');

CREATE TABLE IF NOT EXISTS film_participant
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(128) NOT NULL,
    last_name  VARCHAR(128) NOT NULL,
    role       VARCHAR(32)  NOT NULL
);

INSERT INTO film_participant (first_name, last_name, role)
VALUES ('Kim', 'Do-gi', 'ACTOR'),
       ('Park', 'Joong-woo', 'PRODUCER');

CREATE TABLE IF NOT EXISTS comment
(
    id       BIGSERIAL PRIMARY KEY,
    film_id  INT REFERENCES film (id),
    users_id INT REFERENCES users (id),
    text     VARCHAR(128) NOT NULL,
    rating   NUMERIC
);

INSERT INTO comment (film_id, users_id, text, rating)
VALUES (1, 1, 'Great revenge style serial.', 9),
       (2, 1, 'Interesting korean drama. Waiting for the next season.', 8);

CREATE TABLE IF NOT EXISTS film_participant_film
(
    film_participant_id INT REFERENCES film_participant (id),
    film_id             INT REFERENCES film (id)
);


