DROP TABLE chargeStatus IF EXISTS;

CREATE TABLE chargeStatus(
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    primer_nombre VARCHAR(20),
    segundo_nombre VARCHAR(20),
    writeSuccess VARCHAR(13),
)