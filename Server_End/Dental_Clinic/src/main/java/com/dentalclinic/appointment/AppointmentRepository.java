package com.dentalclinic.appointment;

import java.util.Collection;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dentalclinic.dentist.Dentist;
import com.dentalclinic.patient.Patient;

/**
 * Repository class for <code>Appointment</code> domain objects 
 * @author Xiangting Fan
 */

public interface AppointmentRepository extends Repository<Appointment, Integer> {
	
	void save(Appointment appointment);
	
	@Transactional(readOnly=true)
	@Cacheable("appointmentList")
	Collection<Appointment> findAll() throws DataAccessException;
	
	@Transactional(readOnly = true)
	Appointment findById(Integer id);
	
	@Query("SELECT apptype FROM AppointmentType apptype ORDER BY apptype.name")
    @Transactional(readOnly = true)
    List<AppointmentType> findAppointmentTypes();
	
	@Query("SELECT appdentist FROM Dentist appdentist ORDER BY appdentist.lastName")
    @Transactional(readOnly = true)
    List<Dentist> findAppointmentDentists();
	
	@Query("SELECT apppatient FROM Patient apppatient ORDER BY apppatient.lastName")
    @Transactional(readOnly = true)
    List<Patient> findAppointmentPatients();
		
}
