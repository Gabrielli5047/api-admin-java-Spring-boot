CREATE DATABASE desafioDois;

create table administrators(
  id serial not null primary key,
  name varchar(50) not null,
  email varchar(50) not null unique,
  password text not null
);

drop table administrators;


insert into administrators (name, email, password) values ('Frankilns','Frankilns@email.com','senhaqualquer');
insert into administrators (name, email, password) values ('Bianca','Bianca@email.com','senhaqualquer');
insert into administrators (name, email, password) values ('Gabrielli','Gabrielli@email.com', 'senhaqualquer');
insert into administrators (name,email,  password) values ('Mayanna','Mayanna@email.com', 'senhaqualquer');
insert into administrators (name,email,  password) values ('Lukas','Lukas@email.com','senhaqualquer');
insert into administrators (name,email,  password) values ('teste','teste@email.com','senhaqualquer');

drop table users;

create table users(
  id serial not null primary key,
  name varchar(50) not null,
  cpf varchar(14) not null unique,
  telephone varchar(20) not null unique,
  birth_date timestamp default current_timestamp,
  email varchar(50) not null unique
);



insert into users (name, cpf, telephone, birth_date, email) values ('Frankilns', '04830213507','71997169443','11-21-1998', 'Frankilns@email.com');
insert into users (name, cpf, telephone, birth_date, email) values ('Gabrielli', '24850213507','71997684443','11-21-1998', 'gabrielli@email.com');
insert into users (name, cpf, telephone, birth_date, email) values ('Mayanna', '34836213507','71997189543','11-21-1998', 'mayanna@email.com');
insert into users (name, cpf, telephone, birth_date, email) values ('Lukas', '44830213707','71997459443','11-21-1998', 'lukkas@email.com');
insert into users (name, cpf, telephone, birth_date, email) values ('Bianca', '54850213507','71957189443','11-21-1998', 'bianca@email.com');


drop table request;

create table request(
  id serial not null primary key,
  user_id integer not null,
  value_total integer not null,
  decription varchar(250) not null,
  date_creation timestamp default current_timestamp,
  status varchar(12) not null default 'em_aberto',
  constraint fk_user foreign key (user_id) references users(id)
);


