package mx.com.axity.zuul.services;

import mx.com.axity.zuul.to.ValidTokenRequestTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "OAUTH-SERVICE")
public interface ITokenService {

    @RequestMapping(method = RequestMethod.POST,value="/oauthrs/validate")
    ResponseEntity<Object> validateToken(@RequestBody ValidTokenRequestTO token);
}
