CREATE SCHEMA core;

CREATE TABLE core.CUP
(
    id   INT PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

INSERT INTO core.CUP
VALUES (1, 'ironMan');

INSERT INTO core.CUP
VALUES (2, 'xyz');


CREATE SCHEMA reconcile;

CREATE TABLE reconcile.customer
(
    id   INT PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    age  VARCHAR(64) NOT NULL
);

INSERT INTO reconcile.customer
VALUES (1, 'khang', '100');

INSERT INTO reconcile.customer
VALUES (2, '12', '12');