create table remedio(
id bigint not null auto_increment,
nome varchar (100) not null,
via varchar (100) not null,
sintomas varchar (100) not null,
quantidade int (20) not null,
validade varchar (100) not null,

primary key(id)
);