# Dental-Clinic

## Prepared: Mysql
Create a database used the commends in DB_Dental_Clinic.sql:

####################

create database DB_Dental_Clinic; 

create user 'dentalUser'@'localhost' identified by 'DentalPassword';

grant all on DB_Dental_Clinic.* to 'dentalUser'@'localhost';

####################

Then check src/main/resouces/application.properties, make sure you have the correct username and password of mysql.

## Gradle build and run:

./gradlew clean build && java -jar build/libs/Dental-Clinic-0.1.0.jar


## get the appointmentTypes API: 
http://localhost:8001/appointment/appointmentType.json

## Based on the appointmentType ID get the list of dentists:
http://localhost:8001/dentist/querybyAppointmentType?Aptype_id=1 

