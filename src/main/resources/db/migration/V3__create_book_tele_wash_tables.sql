CREATE TABLE IF NOT EXISTS book (id SERIAL primary key,
number_seller varchar(255) not null,
type varchar(255) DEFAULT 'Book',
price double precision not null,
name varchar(255) not null,
author_id int not null,
FOREIGN KEY (author_id) REFERENCES author (id) ON DELETE CASCADE,
number int DEFAULT 0
);

CREATE TABLE IF NOT EXISTS telephone (id SERIAL primary key,
performance int not null,
accSize int not null,
number_seller varchar(255) not null,
type varchar(255) DEFAULT 'Electronic',
price double precision not null,
name varchar(255) not null,
number int DEFAULT 0
);

CREATE TABLE IF NOT EXISTS washing_machine (id SERIAL primary key,
performance int not null,
tankSize int not null,
number_seller varchar(255) not null,
type varchar(255) DEFAULT 'Plumbing',
price double precision not null,
name varchar(255) not null,
number int DEFAULT 0
);

INSERT INTO book (number_seller, price, name, author_id, number) VALUES
('+759394343242', 1500, 'Book name 1', 1, 2),
('+74634732243729', 1200, 'Book name 2', 1, 1);

INSERT INTO telephone (performance, accSize, number_seller, price, name, number) VALUES
(5000, 2500, '+732246364733', 150000, 'IPHONE 15', 3);

INSERT INTO washing_machine (performance, tankSize, number_seller, price, name, number) VALUES
(6000, 10000, '+79999999999', 50000, 'Washing Machine', 4);