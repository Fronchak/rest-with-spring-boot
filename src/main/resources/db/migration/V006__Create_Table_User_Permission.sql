create table user_permission (
	id_user int,
	id_permission int,
	primary key (id_user, id_permission),
	foreign key (id_user)
	references users(id),
	foreign key (id_permission)
	references permission(id)
);