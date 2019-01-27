create table opportunities(
	id bigint auto_increment not null,
	name_prospecto varchar(80) not null,
	description varchar(200) not null,
	value decimal(10,2),

	primary key(id)
);