package mx.com.Security.services.service;

public interface IAuthService {

    void validateCredentials(String usr, String password);

    void validateUserExist(String usr);

}
