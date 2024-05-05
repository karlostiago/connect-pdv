package com.connectpdv.pdv.exceptions.builder;

import com.util.commons.exceptions.handler.Error;
import org.springframework.http.HttpStatus;

public class ErrorBuilder {

    private static Error error;

    public ErrorBuilder() {
        error = new Error();
    }

    public static ErrorBuilder builder() {
        return new ErrorBuilder();
    }

    public ErrorBuilder withStatus(HttpStatus status) {
        error.setStatus(status);
        error.setCode(status.value());
        return this;
    }

    public ErrorBuilder withTypeError(String typeError) {
        error.setErrorType(typeError);
        return this;
    }

    public ErrorBuilder withMessage(String message) {
        error.setMessage(message);
        return this;
    }

    public ErrorBuilder withDetails(Throwable ex) {
        error.setDetail(ex);
        return this;
    }

    public Error build() {
        return error;
    }
}
