CREATE TABLE IF NOT EXISTS author (id SERIAL primary key,
name varchar(255) not null,
surname varchar(255) not null);

INSERT INTO author (name, surname) VALUES ('Author', 'Authorov');