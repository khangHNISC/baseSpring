CREATE TABLE person
(
    id         INT GENERATED ALWAYS AS IDENTITY,
    first_name VARCHAR(20),
    last_name  VARCHAR(20),
    age        INT
);

INSERT INTO person
VALUES (default, 'khang', 'le', 90);

INSERT INTO person
VALUES (default, 'kien', 'ho', 25);