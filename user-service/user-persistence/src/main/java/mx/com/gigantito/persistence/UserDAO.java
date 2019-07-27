package mx.com.gigantito.persistence;

import mx.com.gigantito.model.UserDO;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UserDAO extends CrudRepository<UserDO, Long> {

    List<UserDO> findByLastName(String lastName);
}
