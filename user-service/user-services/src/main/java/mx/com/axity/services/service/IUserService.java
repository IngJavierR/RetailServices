package mx.com.axity.services.service;

import mx.com.axity.commons.to.UserTO;
import java.util.List;

public interface IUserService {

    List<UserTO> getUsers();
}
