package mx.com.Security.services.service;

import mx.com.Security.services.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class TokenServiceTest extends BaseTest {

    @Test
    public void emptyTest() {
        Assert.assertTrue(true);
    }

    @Test
    public void shouldGenerateToken() {

        String clientId = "1";
        String user = "test";
        String profile = "admin";

        String token = tokenService.generateToken(clientId, user, profile);

        Assert.assertNotNull(token);
        Assert.assertTrue(token.length() > 30);
    }

    @Test
    public void shouldValidateToken() {

        String clientId = "1";
        String user = "test";
        String profile = "admin";

        String token = tokenService.generateToken(clientId, user, profile);
        tokenService.validateToken(token);
        Assert.assertTrue(true);
    }

    @Test
    public void shouldDecodeValidToken() {

        String clientId = "1";
        String user = "test";
        String profile = "admin";

        String token = tokenService.generateToken(clientId, user, profile);
        Map<String, String> decodedToken = tokenService.decodeToken(token);

        Assert.assertEquals("1", decodedToken.get("clientId"));
        Assert.assertEquals("test", decodedToken.get("user"));
        Assert.assertEquals("admin", decodedToken.get("profile"));
    }
}
