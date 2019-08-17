package mx.com.example.web.form;

import mx.com.example.commons.to.LoginRequestTO;
import mx.com.example.commons.to.TokenResponseTO;
import mx.com.example.services.facade.IOauthserverFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FormController {

    @Autowired
    private IOauthserverFacade oauthserver;

    @GetMapping("oauth")
    public String welcome(@RequestParam("redirect_uri") String redirectUri, @RequestParam("client_id") String clientId, Model model) {

        LoginRequestTO request = new LoginRequestTO();
        request.setRedirectUri(redirectUri);
        request.setClientId(clientId);
        model.addAttribute("loginRequestTO", request);
        return "login.html";
    }

    @RequestMapping(value = "/oauth/authorize", method = RequestMethod.POST)
    public void loginSubmit(@ModelAttribute LoginRequestTO loginRequest, HttpServletResponse response) {

        TokenResponseTO tokenResponse = oauthserver.authorize(loginRequest);

        Cookie cookie = new Cookie("token", tokenResponse.getAccess_token());
        cookie.setPath("/");
        response.addCookie(cookie);
        response.setHeader("Location", loginRequest.getRedirectUri());
        response.setStatus(HttpStatus.SEE_OTHER.value());
    }
}
