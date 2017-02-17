create database db_dental_clinic;
create user 'dentalUser'@'localhost' identified by 'DentalPassword';
grant all on db_dental_clinic.* to 'dentalUser'@'localhost';