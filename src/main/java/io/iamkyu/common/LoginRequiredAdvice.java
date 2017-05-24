package io.iamkyu.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Kj Nam
 * @since 2017-05-24
 */
@ControllerAdvice
public class LoginRequiredAdvice {
    private static final Logger logger = LoggerFactory.getLogger(LoginRequiredAdvice.class);

    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public void unAuthorized() {
        logger.debug("UnAuthorizedException is happened!");
    }
}
