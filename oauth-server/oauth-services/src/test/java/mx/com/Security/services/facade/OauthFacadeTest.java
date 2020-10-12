package mx.com.Security.services.facade;

import mx.com.Security.commons.exceptions.UnAuthorizedException;
import mx.com.Security.commons.to.*;
import mx.com.Security.services.BaseTest;
import mx.com.Security.services.facade.impl.OauthFacade;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

public class OauthFacadeTest extends BaseTest {

    @Value("${security.values.ttl}")
    private int ttl;

    @Test
    public void emptyTest() {
        Assert.assertTrue(true);
    }

    @Test
    public void shouldGenerateToken() {

        LoginRequestTO loginRequestTO = new LoginRequestTO();
        loginRequestTO.setClientId("1");
        loginRequestTO.setPassword("123");
        loginRequestTO.setUser("qwe");

        TokenResponseTO tokenResponseTO = oauthFacade.authorize(loginRequestTO);

        Assert.assertNotNull(tokenResponseTO.getAccess_token());
        Assert.assertTrue(tokenResponseTO.getAccess_token().length() > 30);
        Assert.assertEquals(ttl, tokenResponseTO.getExpires_in());
        Assert.assertEquals("Bearer", tokenResponseTO.getToken_type());
        Assert.assertEquals("", tokenResponseTO.getScope());
    }

    @Test(expected = UnAuthorizedException.class)
    public void shouldReturnUnAuthorizedExceptionInvalidCredentials() {

        LoginRequestTO loginRequestTO = new LoginRequestTO();
        loginRequestTO.setClientId("1");
        loginRequestTO.setPassword("321");
        loginRequestTO.setUser("qwe");

        oauthFacade.authorize(loginRequestTO);
    }

    @Test
    public void shouldValidateToken() {

        LoginRequestTO loginRequestTO = new LoginRequestTO();
        loginRequestTO.setClientId("1");
        loginRequestTO.setPassword("123");
        loginRequestTO.setUser("qwe");

        TokenResponseTO tokenResponseTO = oauthFacade.authorize(loginRequestTO);

        ValidTokenRequestTO validTokenRequestTO = new ValidTokenRequestTO();
        validTokenRequestTO.setToken(tokenResponseTO.getAccess_token());
        validTokenRequestTO.setUser("qwe");

        oauthFacade.validate(validTokenRequestTO);

        Assert.assertTrue(true);
    }

    @Test(expected = UnAuthorizedException.class)
    public void shouldReturnUnAuthorizedExceptionInvalidToken() {

        LoginRequestTO loginRequestTO = new LoginRequestTO();
        loginRequestTO.setClientId("1");
        loginRequestTO.setPassword("123");
        loginRequestTO.setUser("qwe");

        TokenResponseTO tokenResponseTO = oauthFacade.authorize(loginRequestTO);

        ValidTokenRequestTO validTokenRequestTO = new ValidTokenRequestTO();
        validTokenRequestTO.setToken(tokenResponseTO.getAccess_token() + "1");
        validTokenRequestTO.setUser("qwe");

        oauthFacade.validate(validTokenRequestTO);
    }

    @Test
    public void shouldRenewToken() {

        LoginRequestTO loginRequestTO = new LoginRequestTO();
        loginRequestTO.setClientId("1");
        loginRequestTO.setPassword("123");
        loginRequestTO.setUser("qwe");

        TokenResponseTO tokenResponseTO = oauthFacade.authorize(loginRequestTO);

        TokenRenewRequestTO tokenRenewRequestTO = new TokenRenewRequestTO();
        tokenRenewRequestTO.setGrant_type("");
        tokenRenewRequestTO.setRefresh_token(tokenResponseTO.getAccess_token());
        tokenRenewRequestTO.setScope("");

        oauthFacade.renew(tokenRenewRequestTO);
    }

    @Test(expected = UnAuthorizedException.class)
    public void shouldReturnUnAuthorizedExceptionRenewToken() {

        LoginRequestTO loginRequestTO = new LoginRequestTO();
        loginRequestTO.setClientId("1");
        loginRequestTO.setPassword("123");
        loginRequestTO.setUser("qwe");

        TokenResponseTO tokenResponseTO = oauthFacade.authorize(loginRequestTO);

        TokenRenewRequestTO tokenRenewRequestTO = new TokenRenewRequestTO();
        tokenRenewRequestTO.setGrant_type("");
        tokenRenewRequestTO.setRefresh_token(tokenResponseTO.getAccess_token() + "1");
        tokenRenewRequestTO.setScope("");

        oauthFacade.renew(tokenRenewRequestTO);
    }

}
