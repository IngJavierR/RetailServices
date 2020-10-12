package mx.com.axity.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import mx.com.axity.zuul.services.ITokenService;
import mx.com.axity.zuul.to.ValidTokenRequestTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public class SimpleFilter extends ZuulFilter {

    final static Logger log = Logger.getLogger(SimpleFilter.class);

    @Autowired
    private ITokenService tokenService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        if(request.getRequestURI().contains("/api/oauth/oauthrs")) {
            return null;
        }

        log.info("Headers Authorization: " + request.getHeader("Authorization"));
        log.info("Headers User: " + request.getHeader("User"));
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        /* Esta seccion valida el token de cada petición contra un servicio OAuth */
        /* Si no se requiere la validación eliminar la sección*/
        ValidTokenRequestTO validTokenRequest = new ValidTokenRequestTO();
        validTokenRequest.setToken(request.getHeader("Authorization").replace("Bearer ", ""));
        validTokenRequest.setUser(request.getHeader("User"));

        try {

            ResponseEntity<Object> response = tokenService.validateToken(validTokenRequest);

        }catch (Exception e) {
            log.info("Exception Ocurred");
            log.info("Cause: {}", e.getCause());
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("Petición inválida");
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        /* Esta seccion valida el token de cada petición contra un servicio OAuth */

        return null;
    }
}
