
create table Account (id int8 not null, principle varchar(255), person_id int8, primary key (id));
create table Person (id int8 not null, firstName varchar(255), lastName varchar(255), photoURL varchar(255), status varchar(255), primary key (id));

alter table Account add constraint FK_k8y6ux5wabtk1los50nb076g0 foreign key (person_id) references Person;
create sequence hibernate_sequence start 1 increment 1;