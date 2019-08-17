package mx.com.example.services.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import mx.com.example.commons.constants.ErrorMessages;
import mx.com.example.commons.exceptions.UnAuthorizedException;
import mx.com.example.commons.to.UserTO;
import mx.com.example.model.UserDO;
import mx.com.example.persistence.UserDAO;
import mx.com.example.services.service.ITokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.lang.reflect.Type;
import java.util.List;

@Service
public class TokenServiceImpl implements ITokenService {

    static final Logger LOG = LogManager.getLogger(TokenServiceImpl.class);

    @Value("${security.values.secret}")
    private String secretWord;

    @Value("${security.values.ttl}")
    private int ttl;

    @Override
    public String generateToken(String clientId, String user, String profile) {
        String token;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretWord);
            token = JWT.create()
                    .withClaim("clientId", clientId)
                    .withClaim("user", user)
                    .withClaim("profile", profile)
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new UnAuthorizedException(ErrorMessages.CANNOT_CREATE_TOKEN, exception);
        }
        return token;
    }

    @Override
    public void validateToken(String token) {

    }

    @Override
    public void getPayload(String token) {

    }
}
