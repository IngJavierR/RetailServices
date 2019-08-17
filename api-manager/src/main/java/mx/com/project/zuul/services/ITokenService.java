package mx.com.project.zuul.services;

import mx.com.project.zuul.to.ValidateTokenRequestTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "OAUTHSERVER-SERVICE")
public interface ITokenService {

    @RequestMapping(method = RequestMethod.POST, value = "/oauthserver/validate")
    ResponseEntity validateToken(ValidateTokenRequestTO validateTokenRequest);
}
