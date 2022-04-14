create table lesson (id bigint not null auto_increment, add_info varchar(255), description varchar(255),
name varchar(255) not null, author_id bigint not null, primary key (id)) engine=InnoDB;
create table lesson_persons (lesson_id bigint not null, persons_id bigint not null, primary key (lesson_id, persons_id)) engine=InnoDB;
create table person (id bigint not null auto_increment, email varchar(255) not null, first_name varchar(255) not null,
last_name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB;
alter table person drop index if exists UK_fwmwi44u55bo4rvwsv0cln012;
alter table person add constraint UK_fwmwi44u55bo4rvwsv0cln012 unique (email);
alter table lesson add constraint FKk5h3njcv0rseb1q7hwexxon1i foreign key (author_id) references person (id);
alter table lesson_persons add constraint FKnuh05x2hpo27591a1fe6mvlms foreign key (persons_id) references person (id);
alter table lesson_persons add constraint FK6r43nal3uk3wm64gg6ec64xkh foreign key (lesson_id) references lesson (id);