package mx.com.Security.services.service;

import mx.com.Security.commons.exceptions.UnAuthorizedException;
import mx.com.Security.services.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class AuthServiceTest extends BaseTest {

    @Test
    public void emptyTest() {
        Assert.assertTrue(true);
    }

    @Test
    public void shouldReturnValidCredentials() {
        authService.validateCredentials("qwe", "123");
        Assert.assertTrue(true);
    }

    @Test(expected = UnAuthorizedException.class)
    public void shouldReturnExceptionWithInvalidCredentials() {
        authService.validateCredentials("qwe", "321");
        Assert.assertTrue(true);
    }
}
