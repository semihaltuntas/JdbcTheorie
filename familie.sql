set names utf8mb4;
set charset utf8mb4;

drop database if exists familie;
create database familie charset utf8mb4;

use familie;

create table personen (
  id int unsigned not null auto_increment primary key,
  voornaam varchar(50) not null,
  papaId int unsigned,
  mamaId int unsigned,
  vermogen decimal(10,2) not null default 0,
  constraint personenPersonenPapa foreign key (papaId) references personen(id),
  constraint personenPersonenMama foreign key (mamaId) references personen(id)  
);

insert into personen(voornaam, vermogen) values
('Hans', 900),
('Alexandra', 600),
('Hendrik', 800),
('Isabelle', 500);

insert into personen(voornaam, papaId, mamaId, vermogen) values
('Jules',3,4,200);

insert into personen(voornaam, papaId, mamaId) values
('Aeneas',1,2),
('Alissia',1,2),
('Anaïs',1,2),
('Ludovic',3,4),
('Merlijn',5,7),
('Merlina',5,7);

create user if not exists cursist identified by 'cursist';
grant select, insert, update, delete on personen to cursist;