package mx.com.Security.services.facade;

import mx.com.Security.commons.to.*;

public interface IOauthFacade {

    TokenResponseTO authorize(LoginRequestTO loginRequest);

    void validate(ValidTokenRequestTO validTokenRequest);

    TokenRefreshResponseTO renew(TokenRenewRequestTO tokenRenewRequest);

}
