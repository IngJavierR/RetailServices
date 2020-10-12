package mx.com.Security.commons.aspects;

import mx.com.Security.commons.exceptions.BusinessException;
import mx.com.Security.commons.exceptions.UnAuthorizedException;
import mx.com.Security.commons.to.ErrorResponseTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Aspect
@Configuration
public class RequestValidatorAspect {

    static final Logger LOG = LogManager.getLogger(RequestValidatorAspect.class);

    @Around(value = "execution(* mx.com.Security.web.rest.*.*(..))  && args(..)")
    public ResponseEntity execute(ProceedingJoinPoint joinPoint) {
        ResponseEntity result;
        try {
            LOG.info("Access");
            LOG.info(String.format("Execution: %s", joinPoint.getSignature()));
            result = (ResponseEntity) joinPoint.proceed();
            return result;
        }
        catch (Throwable e) {
            LOG.info("Throwable Exception Ocurred");
            LOG.info("Signature: {}", joinPoint.getSignature());
            LOG.info("Message: {}", e.getMessage());
            LOG.info("Cause: {}", e.getCause());
            LOG.info("ClassName: {}", e.getClass().getName());

            ErrorResponseTO errorResponse = new ErrorResponseTO();
            errorResponse.setErrorCode(0);
            if(e.getCause() != null) {
                errorResponse.setErrorMessage(e.getCause().getLocalizedMessage());
            }
            errorResponse.setUserError(e.getMessage());
            errorResponse.setInfo("");

            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            if(e instanceof BusinessException) {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            else if(e instanceof UnAuthorizedException) {
                status = HttpStatus.UNAUTHORIZED;
            }

            return ResponseEntity.status(status).body(errorResponse);
        }
    }
}
