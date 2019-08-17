package mx.com.example.services.facade;

import mx.com.example.commons.to.LoginRequestTO;
import mx.com.example.commons.to.TokenResponseTO;

public interface IOauthserverFacade {

    public TokenResponseTO authorize(LoginRequestTO loginRequest);

}
