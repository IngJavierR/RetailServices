package mx.com.Security.commons.to;

import java.io.Serializable;

public class ValidTokenRequestTO implements Serializable {

    private String user;
    private String token;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
