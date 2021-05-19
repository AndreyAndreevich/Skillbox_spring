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
