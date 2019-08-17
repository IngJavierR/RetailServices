package mx.com.example.services.service;

public interface IAuthUserService {

    void validateCredentials(String user, String password);

    void validateUserExist(String user);
}
