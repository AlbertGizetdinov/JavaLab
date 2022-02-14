create table file (
    id serial primary key,
    size bigint not null,
    original_name varchar not null,
    uuid_name varchar not null,
    mime_type varchar not null
);

drop table file;
