package mx.com.gigantito.services.facade.impl;

import mx.com.gigantito.commons.to.UserTO;
import mx.com.gigantito.services.facade.IUserFacade;
import mx.com.gigantito.services.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserFacade implements IUserFacade {

    @Autowired
    private IUserService userService;

    public List<UserTO> getAllUsers() {
        return this.userService.getUsers();
    }
}
