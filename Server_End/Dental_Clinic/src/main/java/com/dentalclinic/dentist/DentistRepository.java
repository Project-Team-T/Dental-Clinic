package com.dentalclinic.dentist;

import java.util.Collection;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dentalclinic.dentist.Dentist;
import com.dentalclinic.patient.Patient;
import com.dentalclinic.queryModel.DentistInfoWithAppointmentType;

/**
 * @author Xiangting Fan
 */

public interface DentistRepository extends Repository<Dentist, Integer> {
	
	/**
     * Retrieve a {@link Dentist} from the data store by id.
     * @param id the id to search for
     * @return the {@link Dentist} if found
     */
    @Transactional(readOnly = true)
    Dentist findById(Integer id);

    /**
     * Save a {@link Dentist} to the data store, either inserting or updating it.
     * @param Dentist the {@link Dentist} to save
     */
    void save(Dentist dentist);
    
    @Transactional(readOnly = true)
    @Cacheable("dentistList")
    Collection<Dentist> findAll() throws DataAccessException;
    
    
    @Query("SELECT dt.id as id, dt.firstName as firstName, dt.lastName as lastName FROM Dentist dt")
    @Transactional(readOnly = true)
    @Cacheable("dentistNameList")
    Collection<? extends Dentist> findDentistNames() throws DataAccessException;
    
    public final static String Query_dentist_by_appointmenttype = 
    		"SELECT dt.id, dt.firstName, dt.lastName FROM Dentist dt"
    		+ " ";
    @Query("SELECT new com.dentalclinic.queryModel.DentistInfoWithAppointmentType(dt.id, dt.firstName, dt.lastName)"
    		+ " FROM Dentist dt join dt.dentistAppointmentTypes dat"
    		+ " where dat.appointmentType.id = :id")
    @Transactional(readOnly = true)
    @Cacheable("dentistListwithAppType")
    Collection<DentistInfoWithAppointmentType> findDentistbyAppType(@Param("id") int apptypeId) throws DataAccessException;
    
       
}

