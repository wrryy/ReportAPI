package pl.wojrydz.softwareplant.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = {ApplicationException.class})
    protected ResponseEntity<Object> handleApplicationException(ApplicationException ex, WebRequest request) {
        ex.printStackTrace();
        ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getMessage(), 500);
        return ResponseEntity.status(500).body(exceptionMessage);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    protected ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
        ex.printStackTrace();
        ExceptionMessage exceptionMessage = new ExceptionMessage("Something went wrong inside our application. " +
                "Try changing request.", 500);
        return ResponseEntity.status(500).body(exceptionMessage);
    }

}
