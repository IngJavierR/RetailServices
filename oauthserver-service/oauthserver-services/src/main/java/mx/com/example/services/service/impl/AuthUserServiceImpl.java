package mx.com.example.services.service.impl;

import mx.com.example.commons.exceptions.UnAuthorizedException;
import mx.com.example.services.service.IAuthUserService;
import org.springframework.stereotype.Service;

@Service
public class AuthUserServiceImpl implements IAuthUserService {

    @Override
    public void validateCredentials(String user, String password) {
        throw new UnAuthorizedException("Login invalido");
    }

    @Override
    public void validateUserExist(String user) {

    }
}
