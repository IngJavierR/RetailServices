package mx.com.axity.services.facade.impl;

import mx.com.axity.commons.to.UserTO;
import mx.com.axity.services.facade.IUserFacade;
import mx.com.axity.services.service.IUserService;
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
