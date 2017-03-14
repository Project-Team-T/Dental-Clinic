package com.dentalclinic.patient;

import java.util.Collection;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dentalclinic.patient.Patient;

/**
 * Repository class for <code>Patient</code> domain objects 
 * 
 * @author Xiangting Fan
 */
public interface PatientRepository extends Repository<Patient, Integer> {
	
	/**
     * Retrieve a {@link Patient} from the data store by id.
     * @param id the id to search for
     * @return the {@link Patient} if found
     */
    @Transactional(readOnly = true)
    Patient findById(Integer id);

    /**
     * Save a {@link Patient} to the data store, either inserting or updating it.
     * @param Patient the {@link Patient} to save
     */
    void save(Patient patient);
    
    @Transactional(readOnly = true)
    @Cacheable("patientList")
    Collection<Patient> findAll() throws DataAccessException;
}
