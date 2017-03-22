-- Table:  
-- Dentist
-- AppointmentType
-- DentistAppointmentType

--Schedule  (dentist_schedule)

Select d.firstName, d.lastName from Dentist d join DentistAppointmentType dat Where d.id == dat.id;


Select s.* from Schedule s 

@Query("SELECT s.Day, s.Time_begin, s.Time_end FROM Schedule s join s.dentists sd where sd.id = :id")

--sql:
select s.* from DB_Dental_Clinic.schedule s inner join DB_Dental_Clinic.dentist_schedule ds ON s.id = ds.schedule_id where ds.dentist_id =1;



