DROP SCHEMA IF EXISTS universityDB;
CREATE SCHEMA universityDB;

DROP USER IF EXISTS asgarov;
CREATE USER `asgarov`@`localhost` IDENTIFIED BY 'password';

GRANT ALL PRIVILEGES ON * . * TO `asgarov`@`localhost`;
