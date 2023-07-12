use account
DROP TABLE  IF EXISTS user_0;
DROP TABLE  IF EXISTS user_1;
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