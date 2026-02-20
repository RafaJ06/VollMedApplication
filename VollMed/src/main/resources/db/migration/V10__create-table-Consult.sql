create table Consult (
id int not null auto_increment,
doctor_id int not null,
patient_id int not null,
appointment Datetime not null,
primary key(id),
constraint  fk_consult_doctor_id foreign key(doctor_id) references medicos(id),
constraint  fk_consult_patient_id foreign key(patient_id) references patient(id)
 );