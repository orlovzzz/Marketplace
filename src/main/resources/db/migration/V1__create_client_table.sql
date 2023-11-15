CREATE TABLE IF NOT EXISTS client (id SERIAL primary key,
name varchar(255),
surname varchar(255),
email varchar(255),
login varchar(255),
password varchar(255),
role varchar(255)
);

INSERT INTO client(name, surname, email, login, password, role) VALUES ('Artem', 'Davydyuk', 'artdav@gmail.com', 'login', 'password', 'ADMIN');