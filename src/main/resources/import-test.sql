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

insert into sns (sns_id, person_id, contents, create_date, use_yn, dept_id, like_count) values (1, 'urosaria', '안녕하세요, 테스트1입니다.', '2016-06-16','Y', 2, 1);
insert into sns (sns_id, person_id, contents, create_date, use_yn, dept_id, like_count) values (2, 'urosaria', '안녕하세요, 테스트2입니다.', '2016-06-16','Y', 2, 1);
insert into sns (sns_id, person_id, contents, create_date, use_yn, dept_id, like_count) values (3, 'urosaria', '안녕하세요, 테스트3입니다.', '2016-06-16','Y', 2, 1);

insert into file_sns(file_id, path, file_nm, use_yn, file_order, sns_id) values (1, '/test/', 'test.jpg', 'Y', 1, 1);
insert into file_sns(file_id, path, file_nm, use_yn, file_order, sns_id) values (2, '/test/', 'test2.jpg', 'Y', 2, 2);
insert into file_sns(file_id, path, file_nm, use_yn, file_order, sns_id) values (3, '/test/', 'test3.jpg', 'Y', 3, 3);

insert into comment_sns(comment_id, contents, person_id, create_date, use_yn, sns_id) values (1, '댓글테스트1', 'urosaria', '2016-06-26', 'Y', 1);
insert into comment_sns(comment_id, contents, person_id, create_date, use_yn, sns_id) values (2, '댓글테스트2', 'test2', '2016-06-26', 'Y', 1);
insert into comment_sns(comment_id, contents, person_id, create_date, use_yn, sns_id) values (3, '댓글테스트3', 'test3', '2016-06-26', 'Y', 1);

insert into like_sns(like_id, person_id, create_date, use_yn, sns_id) values (1, 'urosaria', '2016-06-26', 'Y', 1);
