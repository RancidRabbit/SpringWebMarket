create table users (id serial primary key
, username varchar (25) not null unique
, password varchar (60) not null
, email varchar (40) not null unique
);


create table roles (id serial
, name varchar (25) not null
, primary key (id));


create table users_roles (user_id integer not null
, role_id integer not null
, primary key (user_id, role_id)
, foreign key (user_id) references users (id)
, foreign key (role_id) references roles (id));

insert into roles (name)
values
('ROLE_ADMIN'),
('ROLE_USER');


insert into users (username, password, email)
values
('user', '$2a$12$NMRIQP9A8/8x4Af3ZKy5b.q429nQhLnae6EVcG.ckbrinu8W9HDoe', 'user@gmail.com'),
('admin', '$2a$12$K.2LYF4MzmVCLImOJK6WjuOS6HBBHZdxocrzjED1bTHUOTioUVAey', 'admin@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 2),
(2, 1);

create table privileges (id serial primary key,
 name varchar (30) not null);

insert into privileges (name)
values
('READ'),
('WRITE'),
('DELETE');

create table roles_privileges (roles_id integer not null,
privileges_id integer not null,
primary key (roles_id, privileges_id),
foreign key (roles_id) references roles (id),
foreign key (privileges_id) references privileges (id)
);

insert into roles_privileges (roles_id, privileges_id)
values
(1, 1),
(1, 2),
(1, 3),
(2, 1);
