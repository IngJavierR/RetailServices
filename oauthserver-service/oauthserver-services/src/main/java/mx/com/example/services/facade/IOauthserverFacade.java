package mx.com.example.services.facade;

import mx.com.example.commons.to.*;

public interface IOauthserverFacade {

    TokenResponseTO authorize(LoginRequestTO loginRequest);

    void validate(ValidateTokenRequestTO validateTokenRequest);

    RenewTokenResponseTO renew(RenewTokenRequestTO renewTokenRequest);

}
