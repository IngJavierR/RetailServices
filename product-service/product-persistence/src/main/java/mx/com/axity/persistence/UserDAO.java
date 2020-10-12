package mx.com.axity.persistence;

import mx.com.axity.model.UserDO;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UserDAO extends CrudRepository<UserDO, Long> {

    List<UserDO> findByLastName(String lastName);
}
