package mx.com.Security.web.forms;

import mx.com.Security.commons.to.LoginRequestTO;
import mx.com.Security.services.facade.IOauthFacade;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginFormController {

    static final Logger LOG = LogManager.getLogger(LoginFormController.class);

    @Autowired
    IOauthFacade iOauthFacade;

    @GetMapping("/oauth")
    public String welcome(@RequestParam("redirect_uri") String redirectUri, @RequestParam("client_id") String clientId, Model model) {
        LoginRequestTO loginRequestTO = new LoginRequestTO();
        loginRequestTO.setRedirectUri( StringUtils.isBlank(redirectUri) ? "http://www.google.com" : redirectUri);
        loginRequestTO.setClientId( StringUtils.isBlank(clientId) ? "" : clientId);
        model.addAttribute("loginRequestTO", loginRequestTO);
        return "loginForm.html";
    }

    @RequestMapping(value = "/oauth/authorize", method = RequestMethod.POST)
    public void loginSubmit(@ModelAttribute LoginRequestTO loginRequestTO, HttpServletResponse response) {
        LOG.info(loginRequestTO);

        String token = iOauthFacade.authorize(loginRequestTO).getAccess_token();

        Cookie tokenCookie = new Cookie("token", token);
        tokenCookie.setHttpOnly(true);
        tokenCookie.setPath("/");
        response.addCookie(tokenCookie);
        response.setHeader("Location",loginRequestTO.getRedirectUri());
        response.setStatus(HttpStatus.SEE_OTHER.value());
    }
}
