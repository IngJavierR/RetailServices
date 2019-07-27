package mx.com.gigantito.services.facade;

import mx.com.gigantito.commons.to.UserTO;

import java.util.List;

public interface IUserFacade {

    List<UserTO> getAllUsers();

}
