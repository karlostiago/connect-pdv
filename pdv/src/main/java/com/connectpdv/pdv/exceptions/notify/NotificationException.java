package com.connectpdv.pdv.exceptions.notify;

import com.util.commons.enums.TypeError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotificationException extends RuntimeException {

    private final TypeError typeError;

    public NotificationException(final String message) {
        super(message);
        typeError = TypeError.ERROR;
    }

    public NotificationException(final String message, TypeError type) {
        super(message);
        this.typeError = type;
    }

    public TypeError getTypeError() {
        return typeError;
    }
}
