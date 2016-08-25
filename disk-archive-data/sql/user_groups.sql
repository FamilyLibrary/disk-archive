#need to be investigated how to use seq_book_groups sequence during inserting records into database
#
DELETE FROM USER_GROUPS WHERE id = 1; 
DELETE FROM GROUPS WHERE name = 'ROLE_USER';

DELETE FROM USER_GROUPS WHERE id = 2; 
DELETE FROM GROUPS WHERE name = 'ROLE_ADMIN';

insert into GROUPS (id, description, name) values (1, 'Role for normal User', 'ROLE_USER');
insert into USER_GROUPS (id) values (1);

insert into GROUPS (id, description, name) values (2, 'Role for normal Admin', 'ROLE_ADMIN');
insert into USER_GROUPS (id) values (2);
