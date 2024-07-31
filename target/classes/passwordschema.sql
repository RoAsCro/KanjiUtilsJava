drop database if exists KanPassword;

create database KanPassword;
use kanpassword;
create table password (
idPassword int primary key,
username varchar(255),
password varchar(255)
);