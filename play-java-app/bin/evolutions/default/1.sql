# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table ingredient (
  id                        bigint not null,
  name                      varchar(255),
  quantity                  integer,
  constraint pk_ingredient primary key (id))
;

create table person (
  id                        bigint not null,
  login                     varchar(255),
  email                     varchar(255),
  name                      varchar(255),
  surname                   varchar(255),
  password                  varchar(255),
  constraint pk_person primary key (id))
;

create table recipe (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_recipe primary key (id))
;


create table recipe_ingredient (
  recipe_id                      bigint not null,
  ingredient_id                  bigint not null,
  constraint pk_recipe_ingredient primary key (recipe_id, ingredient_id))
;
create sequence ingredient_seq;

create sequence person_seq;

create sequence recipe_seq;




alter table recipe_ingredient add constraint fk_recipe_ingredient_recipe_01 foreign key (recipe_id) references recipe (id) on delete restrict on update restrict;

alter table recipe_ingredient add constraint fk_recipe_ingredient_ingredie_02 foreign key (ingredient_id) references ingredient (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists ingredient;

drop table if exists person;

drop table if exists recipe;

drop table if exists recipe_ingredient;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists ingredient_seq;

drop sequence if exists person_seq;

drop sequence if exists recipe_seq;

