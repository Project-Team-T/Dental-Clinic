package com.dentalclinic.dentist;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface ScheduleRepository extends Repository<Schedule, Integer> {
	
	void save(Schedule schedule);
	
	@Transactional(readOnly = true)
    @Cacheable("scheduleList")
    Collection<Schedule> findAll() throws DataAccessException;
	
	/**
     * Retrieve a {@link Schedule} from the data store by id.
     * @param id the id to search for
     * @return the {@link Schedule} if found
     */
    @Transactional(readOnly = true)
    Schedule findById(Integer id);
}