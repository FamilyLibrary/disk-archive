insert into PEOPLE (id, last_name) values (1999, 'Aleksey Timofeev');
insert into USERS (id, login, password, enabled) values (1999, 'alextim', 'alextim', true);

insert into GROUPS (id, description, name) values (1, 'Role for normal User', 'ROLE_USER');
insert into USER_GROUPS (id) values (1);

insert into USERS_USER_GROUPS (user_id, usergroups_id) values (1999, 1);