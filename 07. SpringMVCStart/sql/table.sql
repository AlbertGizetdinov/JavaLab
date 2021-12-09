create table account
(
    id         serial primary key,
    first_name varchar not null,
    last_name  varchar not null
);

drop table account;