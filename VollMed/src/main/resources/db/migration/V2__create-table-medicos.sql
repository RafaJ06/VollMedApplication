create table medicos (
id int not null auto_increment,
name varchar(100),
email varchar(200),
phoneNumber varchar(11),
document varchar(11) not null unique,
specialization varchar(20),
street varchar(100),
number varchar(20),
unit varchar(20),
district varchar(100),
city varchar(100),
state varchar(100),
postalCode varchar(20),
primary key(id)
 );