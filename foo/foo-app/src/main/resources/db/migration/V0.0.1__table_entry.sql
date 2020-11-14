create table entry
(
	id serial not null
		constraint entry_pk
			primary key,
	time timestamp default now() not null,
	comment varchar
);
