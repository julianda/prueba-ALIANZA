package co.com.alianza.fiduciaria.utils;

import co.com.alianza.fiduciaria.api.response.ErrorResponse;
import co.com.alianza.fiduciaria.exception.ClienteException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
@SuppressWarnings("MethodMayBeStatic")
public class Handler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClienteException.class)
    public ResponseEntity<ErrorResponse> handleITenderException(ClienteException exception) {
        log.error(exception.getMessage(), exception);

        if (exception.getErrorCode() != null) {
            return new ResponseEntity<>(
                    new ErrorResponse(exception.getErrorCode().value(), exception.getMessage()),
                    exception.getErrorCode());
        } else {
            if (exception.getCause() != null) {
                log.error(exception.getCause().getMessage(), exception.getCause());
            }
            return new ResponseEntity<>(
                    new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception exception) {
        log.error(exception.getMessage(), exception);
        if (exception.getCause() != null) {
            log.error(exception.getCause().getMessage(), exception.getCause());
        }
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
