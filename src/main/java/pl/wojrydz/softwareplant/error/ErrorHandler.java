package pl.wojrydz.softwareplant.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> generalHandler(Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getMessage());
        return ResponseEntity.status(500).body(exceptionMessage);
    }

}
