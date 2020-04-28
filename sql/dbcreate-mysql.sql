SET NAMES utf8;

DROP DATABASE IF EXISTS AirlinesDB;
CREATE DATABASE AirlinesDB CHARACTER SET utf8 COLLATE utf8_bin;
USE AirlinesDB;

-- ==================================================
-- Create table 'roles' - roles of users in DataBase
-- ==================================================

CREATE TABLE roles (
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(10) NOT NULL UNIQUE
);
-- Fill 'roles' start values
INSERT INTO roles VALUES(0, 'admin');
INSERT INTO roles VALUES(1, 'dispatcher');

-- ===========================================
-- Create table 'users' - description of user
-- ===========================================
CREATE TABLE users(
	id INTEGER NOT NULL auto_increment PRIMARY KEY,
	login VARCHAR (20) NOT NULL UNIQUE,
	password VARCHAR(20) NOT NULL,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	role_id INTEGER NOT NULL REFERENCES roles(id) 
					ON DELETE CASCADE ON UPDATE CASCADE
);
-- Fill 'users' start values
INSERT INTO users VALUES (DEFAULT, 'admin', 'admin', 'Default', 'Admin', 0);
INSERT INTO users VALUES (DEFAULT, 'dispatcher', 'dispatcher', 'Ольга Львовна', 'Проскурина', 1);

-- =======================================================================
-- Create table 'ord-status' - statuses of order from dispatcher to admin
-- =======================================================================

CREATE TABLE ord_status (
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR (10) NOT NULL UNIQUE
);

-- Fill 'ord-status' to values
INSERT INTO ord_status VALUES (0, 'opened');
INSERT INTO ord_status VALUES (1, 'completed');
INSERT INTO ord_status VALUES (2, 'rejected');

-- ==================================================================================
-- Create table 'orders' - orders to administrator (if problems with crew on flight)
-- ==================================================================================

CREATE TABLE orders (
	id INTEGER NOT NULL auto_increment PRIMARY KEY,
	description VARCHAR (320) NOT NULL,
	status_id INTEGER NOT NULL REFERENCES ord_status(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ===================================================
-- Create table 'flight-status' - statuses of flight
-- ===================================================

CREATE TABLE flight_status (
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR (10) NOT NULL UNIQUE
);

-- Fill 'flight-status' to values

INSERT INTO flight_status VALUES (0, 'created');
INSERT INTO flight_status VALUES (1, 'ready');
INSERT INTO flight_status VALUES (2, 'in_flight');
INSERT INTO flight_status VALUES (3, 'landed');
INSERT INTO flight_status VALUES (4, 'complete');

-- ==============================================
-- Create table 'posts' - posts of employees
-- ==============================================

CREATE TABLE posts(
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR (20) NOT NULL UNIQUE
);
-- Fill 'posts' to values
INSERT INTO posts VALUES (0, 'pilot');
INSERT INTO posts VALUES (1, 'navigator');
INSERT INTO posts VALUES (2, 'radioman');
INSERT INTO posts VALUES (3, 'stewardess');

-- ==============================================
-- Create table 'employees' - list of employee
-- ==============================================

CREATE TABLE employees (
	id INTEGER NOT NULL auto_increment PRIMARY KEY,
	first_name VARCHAR (25) NOT NULL,
	last_name VARCHAR (25) NOT NULL,
	post_id INTEGER NOT NULL REFERENCES posts(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Fill 'employees' to values
INSERT INTO employees VALUES(DEFAULT, 'Francis', 'Powers', 0);
INSERT INTO employees VALUES(DEFAULT, 'Николай', 'Гастелло', 0);
INSERT INTO employees VALUES(DEFAULT, 'Gans', 'Ulbrecht', 1);
INSERT INTO employees VALUES(DEFAULT, 'Семен', 'Штурман', 1);
INSERT INTO employees VALUES(DEFAULT, 'Kate', 'Simonce', 2);
INSERT INTO employees VALUES(DEFAULT, 'Нурсумбек', 'Алиев', 2);
INSERT INTO employees VALUES(DEFAULT, 'Жанна', 'Прекрасная', 3);
INSERT INTO employees VALUES(DEFAULT, 'Наталья', 'Ужасная', 3);

-- ==============================================
-- Create table 'flights' - statuses of requests
-- ==============================================

CREATE TABLE flight (
	id INTEGER NOT NULL auto_increment PRIMARY KEY,
	number INTEGER NOT NULL,
	name VARCHAR (50) NOT NULL,
	city_from VARCHAR (20),
	city_to VARCHAR (20),
	flight_date DATE NOT NULL,
	status_id  INTEGER NOT NULL REFERENCES flight_status(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Fill 'flights' to values
INSERT INTO flight VALUES (DEFAULT, 0001, 'Kharkov - Minsk', 'Kharkov', 'Minsk', '2020-05-15', 0);
INSERT INTO flight VALUES (DEFAULT, 0006, 'Харьков - Прага', 'Харьков', 'Прага', '2020-03-28', 0);
INSERT INTO flight VALUES (DEFAULT, 0002, 'New-York - Paris', 'New-York', 'Paris', '2020-06-03', 0);
INSERT INTO flight VALUES (DEFAULT, 0003, 'Berlin - Oslo', 'Berlin', 'Oslo', '2020-04-25', 0);
INSERT INTO flight VALUES (DEFAULT, 0004, 'London - Delhi', 'London', 'Delhi', '2020-03-17', 0);
INSERT INTO flight VALUES (DEFAULT, 0005, 'Prague - Stockholm', 'Prague', 'Stockholm', '2020-07-19', 0);


-- ==============================================
-- Create table 'crew' - statuses of requests
-- ==============================================

CREATE TABLE crew (
	id INTEGER NOT NULL auto_increment PRIMARY KEY,
	first_pilot_id INTEGER NOT NULL REFERENCES emploeeys(id) ON DELETE CASCADE ON UPDATE CASCADE,
	second_pilot_id INTEGER NOT NULL REFERENCES emploeeys(id) ON DELETE CASCADE ON UPDATE CASCADE,
	navigator_id INTEGER NOT NULL REFERENCES emploeeys(id) ON DELETE CASCADE ON UPDATE CASCADE,
	radioman_id INTEGER NOT NULL REFERENCES emploeeys(id) ON DELETE CASCADE ON UPDATE CASCADE,
	stuardess1_id INTEGER NOT NULL REFERENCES emploeeys(id) ON DELETE CASCADE ON UPDATE CASCADE,
	stuardess2_id INTEGER NOT NULL REFERENCES emploeeys(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Fill 'crewss' to values
INSERT INTO crew VALUES(DEFAULT, 1, 2, 3, 5, 7, 8);
INSERT INTO crew VALUES(DEFAULT, 2, 1, 4, 6, 8, 7);
INSERT INTO crew VALUES(DEFAULT, 1, 2, 4, 5, 8, 7);

-- ======================================================
-- Create table 'flights_crew' - connect flight with crew
-- ======================================================

CREATE TABLE flight_crew (
	flight_id INTEGER NOT NULL,
	crew_id INTEGER NOT NULL,
	PRIMARY KEY (flight_id, crew_id),
	FOREIGN KEY (flight_id) REFERENCES flight(id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (crew_id) REFERENCES crew(id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- show databases
-- SELECT * FROM roles;
-- SELECT * FROM users;
-- SELECT * FROM status;
-- SELECT * FROM posts;
-- SELECT * FROM flight;
-- SELECT * FROM employees;
-- SELECT * FROM crew;
-- SELECT * FROM flight_crew;