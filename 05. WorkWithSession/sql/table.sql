create table profile (
    id serial primary key,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    email varchar(50) not null,
    password varchar(100) not null
);

drop table profile;
