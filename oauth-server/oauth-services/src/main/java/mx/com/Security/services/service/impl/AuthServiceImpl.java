package mx.com.Security.services.service.impl;

import mx.com.Security.commons.constants.ErrorMessages;
import mx.com.Security.commons.exceptions.UnAuthorizedException;
import mx.com.Security.model.SecurityDO;
import mx.com.Security.persistence.SecurityDAO;
import mx.com.Security.services.service.IAuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

    static final Logger LOG = LogManager.getLogger(AuthServiceImpl.class);

    @Autowired
    private SecurityDAO securityDAO;

    @Override
    public void validateCredentials(String usr, String password) {

        SecurityDO securityDO = securityDAO.findFirstByUsername(usr);
        if(!password.equals(securityDO.getPassword())){
            throw new UnAuthorizedException(ErrorMessages.INVALID_CREDENTIALS);
        }
    }

    @Override
    public void validateUserExist(String usr) {

    }
}
