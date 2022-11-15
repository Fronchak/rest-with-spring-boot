create table person( 
	id int auto_increment,
	first_name varchar(80) not null,
	last_name varchar(80) not null,
	address varchar(100) not null,
	genre varchar(6) not null,
	primary key (id)	
);