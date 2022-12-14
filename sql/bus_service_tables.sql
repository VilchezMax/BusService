DROP DATABASE IF EXISTS bus_service;
CREATE DATABASE bus_service;
USE bus_service;

SET foreign_key_checks = 0;

DROP TABLE IF EXISTS bus;
CREATE TABLE bus (
	id INT NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS city;
CREATE TABLE city (
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45),
	PRIMARY KEY (id)
);


DROP TABLE IF EXISTS bus_stop;
CREATE TABLE bus_stop (
	id INT NOT NULL AUTO_INCREMENT,
    longitude  INT NOT NULL,
    latitude INT NOT NULL,
    stop_name VARCHAR(50) NOT NULL,
    is_terminal TINYINT NOT NULL,
    city_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (city_id) REFERENCES city (id) ON UPDATE CASCADE
);


DROP TABLE IF EXISTS route;
CREATE TABLE route (
	id INT NOT NULL AUTO_INCREMENT,
	stop_id INT NOT NULL,
    bus_id INT NOT NULL,
    PRIMARY KEY (id),
	FOREIGN KEY (stop_id) REFERENCES bus_stop (id) ON UPDATE CASCADE,
    FOREIGN KEY (bus_id) REFERENCES bus (id) ON UPDATE CASCADE
);



