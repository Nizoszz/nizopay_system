//package com.system.nizopay.http.rest.exception;
//
//import com.system.nizopay.core.exception.AlreadyExistsException;
//import com.system.nizopay.core.exception.ConflictException;
//import com.system.nizopay.core.exception.NotFoundException;
//import com.system.nizopay.core.exception.UnauthorizedException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//@ControllerAdvice
//public class RestHandleException {
//
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
//        return buildResponse(HttpStatus.NOT_FOUND,ex.getMessage());
//    }
//
//    @ExceptionHandler(ConflictException.class)
//    public ResponseEntity<Object> handleConflictException(ConflictException ex) {
//        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
//    }
//
//    @ExceptionHandler(AlreadyExistsException.class)
//    public ResponseEntity<Object> handleInvalidTransactionException(AlreadyExistsException ex) {
//        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
//    }
//
//
//    @ExceptionHandler(UnauthorizedException.class)
//    public ResponseEntity<Object> handleConstraintViolation(UnauthorizedException ex) {
//        return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleGenericException(Exception ex) {
//        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
//    }
//
//    private ResponseEntity<Object> buildResponse(HttpStatus status, String message) {
//        Map<String, Object> body = new HashMap<>();
//        body.put("timestamp",LocalDateTime.now());
//        body.put("status", status.value());
//        body.put("error", status.getReasonPhrase());
//        body.put("message", message);
//        return new ResponseEntity<>(body, status);
//    }
//
//    private Map<String, Object> buildValidationResponse(String message, Map<String, String> errors) {
//        Map<String, Object> body = new HashMap<>();
//        body.put("timestamp", LocalDateTime.now());
//        body.put("status", HttpStatus.BAD_REQUEST.value());
//        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
//        body.put("message", message);
//        body.put("validationErrors", errors);
//        return body;
//    }
//}