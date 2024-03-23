package com.maybank.core.endpoint.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Tewaraj
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(basePackages = {"com.maybank.core.endpoint"})
public class RestExceptionHandler {

    protected static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleBusinessDomainException(Exception e) {
        LOGGER.error(e.getMessage(), e);
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
    }

}
