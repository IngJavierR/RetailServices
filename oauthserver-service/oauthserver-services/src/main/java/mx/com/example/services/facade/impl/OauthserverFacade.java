package mx.com.example.services.facade.impl;

import mx.com.example.commons.to.LoginRequestTO;
import mx.com.example.commons.to.TokenResponseTO;
import mx.com.example.commons.to.UserTO;
import mx.com.example.services.facade.IOauthserverFacade;
import mx.com.example.services.service.IAuthUserService;
import mx.com.example.services.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OauthserverFacade implements IOauthserverFacade {

    @Autowired
    private IAuthUserService authUserService;

    @Autowired
    private ITokenService tokenService;

    @Value("${security.values.ttl}")
    private int ttl;

    @Override
    public TokenResponseTO authorize(LoginRequestTO loginRequest) {

        authUserService.validateCredentials(loginRequest.getUser(), loginRequest.getPassword());

        String token = tokenService.generateToken(loginRequest.getClientId(), loginRequest.getUser(), "");

        TokenResponseTO tokenResponse = new TokenResponseTO();
        tokenResponse.setAccess_token(token);
        tokenResponse.setExpires_in(ttl);
        tokenResponse.setToken_type("Bearer");
        tokenResponse.setScope("");

        return tokenResponse;
    }
}
