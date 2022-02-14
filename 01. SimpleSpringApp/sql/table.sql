create table word_count
(
    word varchar(30) not null,
    amount integer not null,
    file_name varchar(20)
);

truncate table word_count;

drop table word_count;