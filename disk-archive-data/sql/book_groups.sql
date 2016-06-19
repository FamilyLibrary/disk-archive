﻿delete from groups;
delete from book_groups;

insert into groups (id, description, name) values (1,'','Художественная литература');
insert into groups (id, description, name) values (2,'','Современные авторы');
insert into groups (id, description, name) values (3,'','Авторы советского периода');
insert into groups (id, description, name) values (4,'','Романы о любви');
insert into groups (id, description, name) values (5,'','Исторические романы ');
insert into groups (id, description, name) values (6,'','Приключенческие романы');
insert into groups (id, description, name) values (7,'','Ужасы');
insert into groups (id, description, name) values (8,'','Фантастика');
insert into groups (id, description, name) values (9,'','Зарубежная классика');
insert into groups (id, description, name) values (10,'','Русская классика');
insert into groups (id, description, name) values (11,'','Прикладная литература');
insert into groups (id, description, name) values (12,'','Кулинария');
insert into groups (id, description, name) values (13,'','Советы');
insert into groups (id, description, name) values (14,'','Здоровье');
insert into groups (id, description, name) values (15,'','Своими руками');
insert into groups (id, description, name) values (16,'','Мир увлечений');
insert into groups (id, description, name) values (17,'','Психология');
insert into groups (id, description, name) values (18,'','История и факты');
insert into groups (id, description, name) values (19,'','Мир тайн и загадок');
insert into groups (id, description, name) values (20,'','Учебная и справочная литература');
insert into groups (id, description, name) values (21,'','Детские книги');
insert into groups (id, description, name) values (22,'','Детям до 4-х лет ');
insert into groups (id, description, name) values (23,'','Детям 4-6 лет');
insert into groups (id, description, name) values (24,'','Детям 7-12 лет');
insert into groups (id, description, name) values (25,'','Детям от 12 лет');

insert into book_groups (id, parent_id) values (1,1);
insert into book_groups (id, parent_id) values (2,1);
insert into book_groups (id, parent_id) values (3,1);
insert into book_groups (id, parent_id) values (4,1);
insert into book_groups (id, parent_id) values (5,1);
insert into book_groups (id, parent_id) values (6,1);
insert into book_groups (id, parent_id) values (7,1);
insert into book_groups (id, parent_id) values (8,1);
insert into book_groups (id, parent_id) values (9,1);
insert into book_groups (id, parent_id) values (10,1);
insert into book_groups (id, parent_id) values (11,11);
insert into book_groups (id, parent_id) values (12,11);
insert into book_groups (id, parent_id) values (13,11);
insert into book_groups (id, parent_id) values (14,11);
insert into book_groups (id, parent_id) values (15,11);
insert into book_groups (id, parent_id) values (16,11);
insert into book_groups (id, parent_id) values (17,11);
insert into book_groups (id, parent_id) values (18,11);
insert into book_groups (id, parent_id) values (19,11);
insert into book_groups (id, parent_id) values (20,11);
insert into book_groups (id, parent_id) values (21,21);
insert into book_groups (id, parent_id) values (22,21);
insert into book_groups (id, parent_id) values (23,21);
insert into book_groups (id, parent_id) values (24,21);
insert into book_groups (id, parent_id) values (25,21);
