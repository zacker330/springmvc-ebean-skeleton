create table people (
  id                            bigint auto_increment not null,
  creation_time                 datetime(6) not null,
  modification_time             datetime(6) not null,
  name                          varchar(20) not null,
  version                       bigint not null,
  constraint pk_people primary key (id)
);

