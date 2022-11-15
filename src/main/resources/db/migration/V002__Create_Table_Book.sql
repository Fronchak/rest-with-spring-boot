create table book (
	id int auto_increment,
	author varchar(100) not null,
	lauch_date datetime not null,
	price decimal(6, 2) not null,
	title varchar(120) not null,
	primary key (id)
);