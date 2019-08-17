package mx.com.project.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import mx.com.project.zuul.services.ITokenService;
import mx.com.project.zuul.to.ValidateTokenRequestTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

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

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        ValidateTokenRequestTO validateTokenRequest = new ValidateTokenRequestTO();
        validateTokenRequest.setToken(request.getHeader("Authorization").replace("Bearer ", ""));
        validateTokenRequest.setUser(request.getHeader("User"));

        try {
            tokenService.validateToken(validateTokenRequest);
        }catch (Exception e) {

            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("Petición inválida");
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }


        return null;
    }
}
