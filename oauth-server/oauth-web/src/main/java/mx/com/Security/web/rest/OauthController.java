package mx.com.Security.web.rest;

import io.swagger.annotations.Api;
import mx.com.Security.commons.to.*;
import mx.com.Security.services.facade.IOauthFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("oauthrs")
@Api(value="oauthrs", description="Operaciones con oauth")
public class OauthController {

    static final Logger LOG = LogManager.getLogger(OauthController.class);

    @Autowired
    IOauthFacade iOauthFacade;

    @RequestMapping(value = "/authorize", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity validateToken(@RequestBody LoginRequestTO loginRequest) {

        LOG.info("Se invoca /authorize");
        TokenResponseTO tokenResponse = iOauthFacade.authorize(loginRequest);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity validateToken(@RequestBody ValidTokenRequestTO validTokenRequest) {

        LOG.info("Se invoca /validate");
        iOauthFacade.validate(validTokenRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/renew", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity renewToken(@RequestBody TokenRenewRequestTO tokenRenewRequest) {

        LOG.info("Se invoca /renew");
        TokenRefreshResponseTO tokenRefreshResponse = iOauthFacade.renew(tokenRenewRequest);
        return new ResponseEntity<>(tokenRefreshResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = "text/plain")
    public ResponseEntity test() {

        LOG.info("Se invoca /ping");
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }
}
