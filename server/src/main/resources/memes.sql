DROP DATABASE IF EXISTS meme_collection;
CREATE DATABASE meme_collection;
USE meme_collection;

CREATE table memes(
id int primary key auto_increment,
url varchar(255) not null, 
description_pic varchar(255),
date_posted datetime not null
); 