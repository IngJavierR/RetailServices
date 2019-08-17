package mx.com.example.services.service;

import java.util.Map;

public interface ITokenService {

    String generateToken(String clientId, String user, String profile);

    void validateToken(String token);

    Map<String, String> getPayload(String token);
}
