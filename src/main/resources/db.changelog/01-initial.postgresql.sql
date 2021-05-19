CREATE TABLE IF NOT EXISTS authors
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    biography  TEXT
);

CREATE TABLE IF NOT EXISTS books
(
    id            BIGSERIAL PRIMARY KEY,
    pub_date      DATE         NOT NULL,
    is_bestseller SMALLINT     NOT NULL,
    slug          VARCHAR(250) NOT NULL,
    title         VARCHAR(250) NOT NULL,
    image         VARCHAR(250) NOT NULL,
    description   TEXT,
    price         INTEGER      NOT NULL,
    discount      FLOAT        NOT NULL,
    author_id     BIGINT       NOT NULL
);
