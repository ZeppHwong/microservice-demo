drop database if exists account0;
drop database if exists account1;
create database account0;
create database account1;
use account0;
drop table if exists user_0;
drop table if exists user_1;
CREATE TABLE user_0(
   user_id integer NOT NULL AUTO_INCREMENT,
   username varchar(100) NOT NULL,
   password varchar(40) NOT NULL,
   mobile varchar(12),
   PRIMARY KEY (user_id)
)ENGINE=InnoDB;
CREATE TABLE user_1(
   user_id integer NOT NULL AUTO_INCREMENT,
   username varchar(100) NOT NULL,
   password varchar(40) NOT NULL,
   mobile varchar(12),
   PRIMARY KEY (user_id)
)ENGINE=InnoDB;

use account1;
drop table if exists user_0;
drop table if exists user_1;
CREATE TABLE user_0(
   user_id integer NOT NULL AUTO_INCREMENT,
   username varchar(100) NOT NULL,
   password varchar(40) NOT NULL,
   mobile varchar(12),
   PRIMARY KEY (user_id)
)ENGINE=InnoDB;
CREATE TABLE user_1(
   user_id integer NOT NULL AUTO_INCREMENT,
   username varchar(100) NOT NULL,
   password varchar(40) NOT NULL,
   mobile varchar(12),
   PRIMARY KEY (user_id)
)ENGINE=InnoDB;