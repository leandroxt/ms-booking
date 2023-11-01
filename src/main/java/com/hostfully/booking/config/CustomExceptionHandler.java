package com.hostfully.booking.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.InvalidParameterException;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> elementNotFound(NoSuchElementException e) {
        log.warn("an element was not found. class: {}, cause: {}, and message {}",
                e.getClass().getTypeName(),
                e.getCause().getMessage(),
                e.getMessage());

        final ErrorResponse error = ErrorResponse.builder(e, NOT_FOUND, e.getMessage())
                .title(e.getCause().getMessage())
                .detail(e.getMessage())
                .detailMessageCode(e.getLocalizedMessage())
                .build();

        return createResponseEntity(NOT_FOUND, error);
    }

    @ExceptionHandler(InvalidParameterException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> invalidParameterException(InvalidParameterException e) {
        log.warn("an parameter is invalid. class: {}, cause: {}, and message {}",
                e.getClass().getTypeName(),
                e.getCause().getMessage(),
                e.getMessage());

        final ErrorResponse error = ErrorResponse.builder(e, BAD_REQUEST, e.getMessage())
                .title(e.getCause().getMessage())
                .detail(e.getMessage())
                .detailMessageCode(BAD_REQUEST.getReasonPhrase())
                .build();

        return createResponseEntity(BAD_REQUEST, error);
    }

    private ResponseEntity<ErrorResponse> createResponseEntity(final HttpStatus status, final ErrorResponse errorResponse) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);

        return ResponseEntity.status(status)
                .headers(headers)
                .body(errorResponse);
    }
}
