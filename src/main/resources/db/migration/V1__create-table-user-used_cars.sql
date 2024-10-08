CREATE SCHEMA IF NOT EXISTS carsale;



CREATE TABLE IF NOT EXISTS carsale.used_cars
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name         VARCHAR(255),
    manufacturer VARCHAR(255),
    model        VARCHAR(255),
    year         VARCHAR(255),
    color        VARCHAR(255),
    price        VARCHAR(255),
    mileage      VARCHAR(255),
    image        OID,
    CONSTRAINT pk_used_cars PRIMARY KEY (id)
);
CREATE SEQUENCE IF NOT EXISTS carsale.users_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE IF NOT EXISTS carsale.users
(
    id       BIGINT NOT NULL,
    name     VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
);