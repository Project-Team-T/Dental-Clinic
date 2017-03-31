package com.dentalclinic.webcontent;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dentalclinic.dentist.Dentist;

public interface SubBlogRepository extends Repository<Blog, Integer>{
	
	@Transactional(readOnly = true)
    @Cacheable("blogs")
    Collection<Blog> findAll() throws DataAccessException;

	void save(SubBlog subBlog);

	@Transactional(readOnly = true)
    Blog findById(Integer id);
	
////	@Query("SELECT id, name FROM AppointmentType at")
//	@Query("SELECT new com.dentalclinic.queryModel.AppointmentTypeName(at.id, at.name) FROM AppointmentType at")
//    @Transactional(readOnly = true)
//	@Cacheable("appointmentTypes")
//    Collection<AppointmentTypeName> findAppointmentTypeName() throws DataAccessException;	
}
