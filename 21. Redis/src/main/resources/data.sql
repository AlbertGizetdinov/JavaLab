insert into author(first_name, last_name) values ('Автор 1 - имя', 'Автор 1 - фамилия');
insert into author(first_name, last_name) values ('Автор 2 - имя', 'Автор 2 - фамилия');
insert into author(first_name, last_name) values ('Автор 3 - имя', 'Автор 3 - фамилия');
insert into author(first_name, last_name) values ('Автор 4 - имя', 'Автор 4 - фамилия');
insert into author(first_name, last_name) values ('Автор 5 - имя', 'Автор 5 - фамилия');
insert into author(first_name, last_name) values ('Автор 6 - имя', 'Автор 6 - фамилия');
insert into author(first_name, last_name) values ('Автор 7 - имя', 'Автор 7 - фамилия');
insert into author(first_name, last_name) values ('Автор 8 - имя', 'Автор 8 - фамилия');
insert into author(first_name, last_name) values ('Автор 9 - имя', 'Автор 9 - фамилия');
insert into author(first_name, last_name) values ('Автор 10 - имя', 'Автор 10 - фамилия');
insert into author(first_name, last_name) values ('Автор 11 - имя', 'Автор 11 - фамилия');
insert into author(first_name, last_name) values ('Автор 12 - имя', 'Автор 12 - фамилия');
insert into author(first_name, last_name) values ('Автор 13 - имя', 'Автор 13 - фамилия');
insert into author(first_name, last_name) values ('Автор 14 - имя', 'Автор 14 - фамилия');
insert into author(first_name, last_name) values ('Автор 15 - имя', 'Автор 15- фамилия');
insert into author(first_name, last_name) values ('Автор 16 - имя', 'Автор 16 - фамилия');
insert into author(first_name, last_name) values ('Автор 17 - имя', 'Автор 17 - фамилия');
insert into author(first_name, last_name) values ('Автор 18 - имя', 'Автор 18 - фамилия');
insert into author(first_name, last_name) values ('Автор 19 - имя', 'Автор 19 - фамилия');
insert into author(first_name, last_name) values ('Автор 20 - имя', 'Автор 20 - фамилия');

insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 1', 'Текст - 1', 'PUBLISHED', 1);
insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 2', 'Текст - 2', 'PUBLISHED', 2);
insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 3', 'Текст - 3', 'PUBLISHED', 3);
insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 4', 'Текст - 4', 'PUBLISHED', 4);
insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 5', 'Текст - 5', 'PUBLISHED', 5);
insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 6', 'Текст - 6', 'PUBLISHED', 1);
insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 7', 'Текст - 7', 'PUBLISHED', 2);
insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 8', 'Текст - 8', 'PUBLISHED', 3);
insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 9', 'Текст - 9', 'PUBLISHED', 4);
insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 10', 'Текст - 10', 'PUBLISHED', 5);
insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 11', 'Текст - 11', 'PUBLISHED', 1);
insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 12', 'Текст - 12', 'PUBLISHED', 2);
insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 13', 'Текст - 13', 'PUBLISHED', 3);
insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 14', 'Текст - 14', 'PUBLISHED', 4);
insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 15', 'Текст - 15', 'PUBLISHED', 5);
insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 16', 'Текст - 16', 'PUBLISHED', 1);
insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 17', 'Текст - 17', 'PUBLISHED', 2);
insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 18', 'Текст - 18', 'PUBLISHED', 3);
insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 19', 'Текст - 19', 'PUBLISHED', 4);
insert into post(created_at, title, text, state, author_id) values (current_timestamp, 'Пост - 20', 'Текст - 20', 'PUBLISHED', 5);

insert into account(first_name, last_name, email, password, role, state)
values ('albert', 'gizetdinov', 'albert@gmail.com', '$2a$12$DetkLTVcLZx8YpMMfhIsU.wiSSrYICBVzi0jMz5k5uB03v0xIwVL6', 'USER', 'CONFIRMED');