package mx.com.example.services.service;

public interface ITokenService {

    String generateToken(String clientId, String user, String profile);

    void validateToken(String token);

    void getPayload(String token);
}
