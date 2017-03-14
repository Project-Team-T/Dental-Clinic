create database DB_Dental_Clinic;
create user 'dentalUser'@'localhost' identified by 'DentalPassword';
grant all on DB_Dental_Clinic.* to 'dentalUser'@'localhost';