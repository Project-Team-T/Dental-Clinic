package com.dentalclinic.appointment;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dentalclinic.dentist.Dentist;

public interface AppointmentTypeRepository extends Repository<AppointmentType, Integer>{
	
	@Transactional(readOnly = true)
    @Cacheable("appointmentTypes")
    Collection<AppointmentType> findAll() throws DataAccessException;

	void save(AppointmentType appointmentType);

	Object findById(int appointmentTypeId);
	
	@Query("SELECT at.id, at.name FROM AppointmentType at")
    @Transactional(readOnly = true)
    Collection<? extends AppointmentType> findAppointmentTypeName() throws DataAccessException;

}