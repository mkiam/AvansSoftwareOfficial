# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

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
  assigned_to               varchar(255),
  manual                    varchar(255),
  constraint pk_recipe primary key (id))
;

create sequence person_seq;

create sequence recipe_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists person;

drop table if exists recipe;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists person_seq;

drop sequence if exists recipe_seq;

