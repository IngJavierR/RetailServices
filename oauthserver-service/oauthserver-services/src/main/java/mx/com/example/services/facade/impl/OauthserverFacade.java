package mx.com.example.services.facade.impl;

import mx.com.example.commons.to.*;
import mx.com.example.services.facade.IOauthserverFacade;
import mx.com.example.services.service.IAuthUserService;
import mx.com.example.services.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

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

    @Override
    public void validate(ValidateTokenRequestTO validateTokenRequest) {

        authUserService.validateUserExist(validateTokenRequest.getUser());

        tokenService.validateToken(validateTokenRequest.getToken());
    }

    @Override
    public RenewTokenResponseTO renew(RenewTokenRequestTO renewTokenRequest) {

        Map<String, String> payload = tokenService.getPayload(renewTokenRequest.getRefresh_token());

        authUserService.validateUserExist(payload.get("user"));

        String token = tokenService.generateToken(payload.get("clientId"), payload.get("user"), payload.get("profile"));

        RenewTokenResponseTO renewTokenResponse = new RenewTokenResponseTO();
        renewTokenResponse.setAccess_token(renewTokenRequest.getRefresh_token());
        renewTokenResponse.setExpires_in(ttl);
        renewTokenResponse.setToken_type("Bearer");
        renewTokenResponse.setScope("");
        renewTokenRequest.setRefresh_token(token);

        return renewTokenResponse;
    }
}
