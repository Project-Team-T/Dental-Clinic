package dentist;

import org.springframework.data.repository.CrudRepository;
import dentist.Dentist;

public interface DentistRepository extends CrudRepository<Dentist, Long>{

}
