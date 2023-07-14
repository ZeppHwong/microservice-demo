drop database if exists account;
use account
Drop Table user_detail_info
CREATE TABLE user_detail_info(
   user_id integer NOT NULL,
   address varchar(100) NOT NULL,
   age  integer,
   PRIMARY KEY (user_id)
)ENGINE=InnoDB;