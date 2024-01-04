insert into
course(id, name, last_updated_date, created_date, is_deleted)
values(10001, 'JPA in 50 steps', now(), now(), false);
insert into course(id, name, last_updated_date, created_date, is_deleted)
values(10002, 'JPA in 100 steps', now(), now(), false);
insert into course(id, name, last_updated_date, created_date, is_deleted)
values(10003, 'JPA in 100 steps', now(), now(), false);

insert into passport(id, number) values (40001, 'E123456');
insert into passport(id, number) values (40002, 'E123457');
insert into passport(id, number) values (40003, 'E123458');
insert into passport(id, number) values (40004, 'E123459');

insert into student(id, name, passport_id) values (20001, 'Blo the peepepe', 40001);
insert into student(id, name, passport_id) values (20002, 'Bloiii the peepepe', 40002);
insert into student(id, name, passport_id) values (20003, 'Mr meowww', 40003);
insert into student(id, name, passport_id) values (20004, 'Mishi meowww', 40004);

insert into review(id, rating, description, course_id)
values (50001, 'FIVE', 'Great Course', 10001);
insert into review(id, rating, description, course_id)
values (50002, 'FIVE', 'Amazing Course', 10001);
insert into review(id, rating, description, course_id)
values (50003, 'FIVE', 'Good Course', 10002);
insert into review(id, rating, description, course_id)
values (50004, 'FIVE', 'Nice Course', 10001);

insert into student_course(student_id, course_id)
values(20001,10001);
insert into student_course(student_id, course_id)
values(20002,10001);
insert into student_course(student_id, course_id)
values(20003,10001);
insert into student_course(student_id, course_id)
values(20001,10003);