CREATE TABLE IF NOT EXISTS client (id SERIAL primary key,
name varchar(255) not null,
surname varchar(255) not null,
email varchar(255) not null,
login varchar(255) not null,
password varchar(255) not null
);

INSERT INTO client(name, surname, email, login, password) VALUES ('Artem', 'Davydyuk', 'artdav@gmail.com', 'login', 'password');