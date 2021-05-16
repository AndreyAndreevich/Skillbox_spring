CREATE TABLE IF NOT EXISTS authors
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    biography  TEXT
);

CREATE TABLE IF NOT EXISTS books
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(250)          DEFAULT NULL,
    price       VARCHAR(250)          DEFAULT NULL,
    price_old   VARCHAR(250)          DEFAULT NULL,
    title       VARCHAR(250) NOT NULL,
    author_id   BIGINT       NOT NULL,
    rating      FLOAT        NOT NULL DEFAULT 0,
    review      TEXT,
    description TEXT
);

CREATE TABLE IF NOT EXISTS tags
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS tags_ref
(
    id      BIGSERIAL PRIMARY KEY,
    book_id BIGINT NOT NULL,
    tag_id  BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS test_entities
(
    id   BIGSERIAL PRIMARY KEY,
    data VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS customers
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts
(
    id       BIGSERIAL PRIMARY KEY,
    login    VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email    VARCHAR(50) NOT NULL
);
