CREATE TABLE pet
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(64) NOT NULL,
    birth_date date
);