package mx.com.Security.services.service.impl;

import mx.com.Security.services.service.ICipher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class CipherImpl implements ICipher {

    static final Logger LOG = LogManager.getLogger(AuthServiceImpl.class);

    @Override
    public String createSHA(String input) {

        MessageDigest digest;
        byte[] encodedhash = new byte[0];

        try {
            digest = MessageDigest.getInstance("SHA-256");
            encodedhash = digest.digest(
                    input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Error al obtener HASH");
        }
        return bytesToHex(encodedhash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
