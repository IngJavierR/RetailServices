package mx.com.example.services.service.impl;

import mx.com.example.services.service.IAuthUserService;
import org.springframework.stereotype.Service;

@Service
public class AuthUserServiceImpl implements IAuthUserService {

    @Override
    public void validateCredentials(String user, String password) {

    }

    @Override
    public void validateUserExist(String user) {

    }
}
