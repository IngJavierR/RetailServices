package mx.com.Security.services.facade.impl;

import mx.com.Security.commons.to.*;
import mx.com.Security.services.facade.IOauthFacade;
import mx.com.Security.services.service.IAuthService;
import mx.com.Security.services.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OauthFacade implements IOauthFacade {

    @Autowired
    private IAuthService authService;

    @Autowired
    private ITokenService tokenService;

    @Value("${security.values.ttl}")
    private int ttl;


    @Override
    public TokenResponseTO authorize(LoginRequestTO loginRequest) {

        authService.validateCredentials(loginRequest.getUser(), loginRequest.getPassword());

        String token = tokenService.generateToken(loginRequest.getClientId(), loginRequest.getUser(), "admin");

        TokenResponseTO tokenResponse = new TokenResponseTO();
        tokenResponse.setAccess_token(token);
        tokenResponse.setExpires_in(ttl);
        tokenResponse.setToken_type("Bearer");
        tokenResponse.setScope("");

        return tokenResponse;
    }

    @Override
    public void validate(ValidTokenRequestTO validTokenRequest) {

        tokenService.validateToken(validTokenRequest.getToken());
    }

    @Override
    public TokenRefreshResponseTO renew(TokenRenewRequestTO tokenRenewRequest) {

        Map<String, String> claims = tokenService.decodeToken(tokenRenewRequest.getRefresh_token());

        authService.validateUserExist(claims.get("user"));

        String token = tokenService.generateToken(claims.get("clientId"), claims.get("user"), claims.get("profile"));

        TokenRefreshResponseTO tokenRefreshResponse = new TokenRefreshResponseTO();
        tokenRefreshResponse.setAccess_token(token);
        tokenRefreshResponse.setExpires_in(ttl);
        tokenRefreshResponse.setToken_type("Bearer");
        tokenRefreshResponse.setScope("");
        tokenRefreshResponse.setRefresh_token(tokenRenewRequest.getRefresh_token());

        return tokenRefreshResponse;
    }
}
