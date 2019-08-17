package mx.com.example.commons.aspects;

import mx.com.example.commons.exceptions.BusinessException;
import mx.com.example.commons.exceptions.UnAuthorizedException;
import mx.com.example.commons.to.ErrorResponseTO;
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

    @Around(value = "execution(* mx.com.example.web.rest.*.*(..))  && args(..)")
    public ResponseEntity execute(ProceedingJoinPoint joinPoint) {
        ResponseEntity result;

        try {
            LOG.info("Access");
            LOG.info(String.format("Execution: %s", joinPoint.getSignature()));
            result = (ResponseEntity) joinPoint.proceed();
            return result;
        }catch (Throwable e) {
            LOG.info("Exception Ocurred");
            LOG.info("Execution: {}", joinPoint.getSignature());
            LOG.info("Exception: {}", e.getMessage());

            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            ErrorResponseTO errorResponse = new ErrorResponseTO();
            errorResponse.setErrorCode("0");
            if(e.getCause() != null) {
                errorResponse.setErrorMessage(e.getCause().getLocalizedMessage());
            }
            errorResponse.setUserError(e.getMessage());
            errorResponse.setInfo("");

            if(e instanceof UnAuthorizedException) {
                status = HttpStatus.UNAUTHORIZED;
            }

            return ResponseEntity.status(status).body(errorResponse);
        }
    }
}
