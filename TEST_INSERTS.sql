insert into users (username, password, first_name, last_name) 
values ('gregor.mavec@c-automobil-import.si','secure','Gregor','Mavec');

insert into users (username, password, first_name, last_name)
values ('jože.blabla@c-automobil-import.si','not_secure','Jože','BlaBla');

insert into CHECK_IN (check_in_time, check_in_type, user_id) 
values (current_timestamp, 'A', 1);

update CHECK_IN 
set check_in_type = 'O'
where user_id = 1;
