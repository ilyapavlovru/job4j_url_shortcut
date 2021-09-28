create table person
(
    id           serial primary key,
    username     varchar(50)  not null unique,
    password     varchar(100) not null,
    site         varchar(100) not null unique,
    registration boolean default true
);

create table url
(
    id    serial primary key,
    value varchar(300) not null unique,
    code  varchar(20)  not null unique,
    total int default 0
);
