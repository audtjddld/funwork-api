insert into department (id, name, use_yn) VALUES (1, '테스트1', 'Y');
insert into department (id, name, use_yn, parent_id) VALUES (2, '테스트1-1', 'Y', 1);
insert into department (id, name, use_yn, parent_id) VALUES (3, '테스트1-1-1', 'Y', 2);
insert into department (id, name, use_yn, parent_id) VALUES (4, '테스트1-2', 'Y', 1);

--

insert into person (id, email, name, passwd, position, security_grade) VALUES (1, 'test1@funwork.io', 'test1', '1234', '사원', 1);
insert into person (id, email, name, passwd, position, security_grade) VALUES (2, 'test2@funwork.io', 'test2', '1234', '대리', 1);
insert into person (id, email, name, passwd, position, security_grade) VALUES (3, 'test3@funwork.io', 'test3', '1234', '과장', 1);

--

insert into department_person (id, dept_id, person_id) VALUES (1, 2, 1);
insert into department_person (id, dept_id, person_id) VALUES (2, 3, 2);
insert into department_person (id, dept_id, person_id) VALUES (3, 3, 3);

insert into sns (id, person_id, contents, create_date, use_yn, dept_id, like_count) values (1, 'urosaria', 'testest11', '2016-06-16','Y', 2, 1);
insert into sns (id, person_id, contents, create_date, use_yn, dept_id, like_count) values (2, 'urosaria', 'testest22', '2016-06-16','Y', 2, 1);
insert into sns (id, person_id, contents, create_date, use_yn, dept_id, like_count) values (3, 'urosaria', 'testest33', '2016-06-16','Y', 2, 1);