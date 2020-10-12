package mx.com.Security.services.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import mx.com.Security.commons.constants.ErrorMessages;
import mx.com.Security.commons.exceptions.BusinessException;
import mx.com.Security.commons.exceptions.UnAuthorizedException;
import mx.com.Security.services.service.ITokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
                    .withExpiresAt(Date.from(Instant.now().plusSeconds(ttl)))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            LOG.error("Cannot generate a token", exception);
            throw new BusinessException(ErrorMessages.FAILED_TO_GET_TOKEN, exception);
        }
        return token;
    }

    @Override
    public void validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretWord);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            verifier.verify(token);
        } catch (JWTVerificationException exception){
            LOG.error("Invalid Token", exception);
            throw new UnAuthorizedException(ErrorMessages.INVALID_TOKEN, exception);
        }
    }

    @Override
    public void revokeToken() {

    }

    @Override
    public Map<String, String> decodeToken(String token) {
        DecodedJWT jwt;
        Map<String, String> claims = new HashMap<>();
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretWord);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            jwt = verifier.verify(token);
        } catch (JWTVerificationException exception){
            LOG.error("Invalid Token", exception);
            throw new UnAuthorizedException(ErrorMessages.INVALID_TOKEN, exception);
        }

        jwt.getClaims().forEach((k , v) -> {
            claims.put(k, v.asString());
        });
        return claims;
    }
}
