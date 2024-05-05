package com.connectpdv.pdv.exceptions.handler;

import com.connectpdv.pdv.exceptions.builder.ErrorBuilder;
import com.connectpdv.pdv.exceptions.notify.NotificationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import com.util.commons.exceptions.handler.Error;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler({NotificationException.class})
    public ResponseEntity<List<Error>> handleNotificationException(NotificationException ex) {
        List<Error> errors = List.of(ErrorBuilder.builder()
                .withStatus(HttpStatus.BAD_REQUEST)
                .withTypeError(ex.getTypeError().name())
                .withMessage(ex.getMessage())
                .withDetails(ex)
                .build());
        return ResponseEntity.badRequest().body(errors);
    }
}
