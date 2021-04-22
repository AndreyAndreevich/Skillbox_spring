DROP TABLE if EXISTS authors;
DROP TABLE if EXISTS books;

CREATE TABLE authors
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name  VARCHAR(50)
);

CREATE TABLE books
(
    id        BIGSERIAL PRIMARY KEY,
    title     VARCHAR(250) NOT NULL,
    price_old VARCHAR(250) DEFAULT NULL,
    price     VARCHAR(250) DEFAULT NULL,
    author_id BIGSERIAL,
    FOREIGN KEY (author_id) REFERENCES authors (id)
);
