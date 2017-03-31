package com.dentalclinic.webcontent;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface BlogTypeRepository extends Repository<BlogType, Integer>{
	
	@Transactional(readOnly = true)
    @Cacheable("blogTypes")
    Collection<BlogType> findAll() throws DataAccessException;

	void save(BlogType blogType);

	Object findById(int blogTypeId);
	
////	@Query("SELECT id, name FROM AppointmentType at")
//	@Query("SELECT new com.dentalclinic.queryModel.AppointmentTypeName(at.id, at.name) FROM AppointmentType at")
//    @Transactional(readOnly = true)
//	@Cacheable("appointmentTypes")
//    Collection<AppointmentTypeName> findAppointmentTypeName() throws DataAccessException;	
}
