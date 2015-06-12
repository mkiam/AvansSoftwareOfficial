# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table manual (
  id                        bigint not null,
  text                      varchar(255),
  recipe_id                 bigint,
  constraint pk_manual primary key (id))
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

create sequence manual_seq;

create sequence person_seq;

create sequence recipe_seq;

alter table manual add constraint fk_manual_recipe_1 foreign key (recipe_id) references recipe (id) on delete restrict on update restrict;
create index ix_manual_recipe_1 on manual (recipe_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists manual;

drop table if exists person;

drop table if exists recipe;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists manual_seq;

drop sequence if exists person_seq;

drop sequence if exists recipe_seq;

