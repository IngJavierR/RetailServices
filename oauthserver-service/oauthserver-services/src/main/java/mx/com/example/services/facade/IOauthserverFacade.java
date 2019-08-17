package mx.com.example.services.facade;

import mx.com.example.commons.to.LoginRequestTO;
import mx.com.example.commons.to.TokenResponseTO;
import mx.com.example.commons.to.ValidateTokenRequestTO;

public interface IOauthserverFacade {

    TokenResponseTO authorize(LoginRequestTO loginRequest);

    void validate(ValidateTokenRequestTO validateTokenRequest);

}
