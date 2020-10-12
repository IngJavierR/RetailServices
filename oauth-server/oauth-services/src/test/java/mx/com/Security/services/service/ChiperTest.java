package mx.com.Security.services.service;

import mx.com.Security.services.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ChiperTest extends BaseTest {

    @Test
    public void emptyTest() {
        Assert.assertTrue(true);
    }

    @Test
    public void shouldGenerateSHA() {

        String word = "TestToSHA";

        String shaOutput = cipher.createSHA(word);

        Assert.assertNotNull(shaOutput);
        Assert.assertTrue(shaOutput.length() > 10);
    }

    @Test(expected = NullPointerException.class)
    public void shouldGenerateExceptionWithNullWord() {

        String word = null;

        String shaOutput = cipher.createSHA(word);
    }
}
