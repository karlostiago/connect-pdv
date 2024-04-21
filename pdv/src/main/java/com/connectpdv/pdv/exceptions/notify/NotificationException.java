package com.connectpdv.pdv.exceptions.notify;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotificationException extends RuntimeException {

    public NotificationException(final String message) {
        super(message);
    }
}
