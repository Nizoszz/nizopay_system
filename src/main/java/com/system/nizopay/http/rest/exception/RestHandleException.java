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
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//import java.time.LocalDateTime;
//
//@ControllerAdvice
//public class RestHandleException extends ResponseEntityExceptionHandler{
//    @ExceptionHandler(ConflictException.class)
//    public ResponseEntity<ConflictExceptionDetails> handleConflicException(ConflictException ex) {
//        return new ResponseEntity<>(ConflictExceptionDetails.builder()
//                                            .timestamp(LocalDateTime.now())
//                                            .error("Bad request exception")
//                                            .status(HttpStatus.BAD_REQUEST.value())
//                                            .details(ex.getMessage())
//                                            .build(),
//                                    HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<NotFoundExceptionDetails> handleNotFoundException(NotFoundException ex) {
//        return new ResponseEntity<>(
//                NotFoundExceptionDetails.builder()
//                        .timestamp(LocalDateTime.now())
//                        .error("Not found")
//                        .status(HttpStatus.NOT_FOUND.value())
//                        .details(ex.getMessage())
//                        .build(), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(AlreadyExistsException.class)
//    public ResponseEntity<AlreadyExistsExceptionDetails> handleEmailAlreadyExistException(AlreadyExistsException ex) {
//        return new ResponseEntity<>(
//                AlreadyExistsExceptionDetails.builder()
//                        .timestamp(LocalDateTime.now())
//                        .error("Conflict")
//                        .status(HttpStatus.CONFLICT.value())
//                        .details(ex.getMessage())
//                        .build(), HttpStatus.CONFLICT);
//    }
//
//    @ExceptionHandler(UnauthorizedException.class)
//    public ResponseEntity<UnauthorizedExceptionDetails> handleUnauthorized(UnauthorizedException ex) {
//        return new ResponseEntity<>(
//                UnauthorizedExceptionDetails.builder()
//                        .timestamp(LocalDateTime.now())
//                        .error("Conflict")
//                        .status(HttpStatus.UNAUTHORIZED.value())
//                        .details(ex.getMessage())
//                        .build(), HttpStatus.UNAUTHORIZED);
//    }
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<InternalServerErrorExceptionDetails> handleInternalServerErrorException(Exception ex) {
//        InternalServerErrorExceptionDetails errorDetails = InternalServerErrorExceptionDetails.builder()
//                .timestamp(LocalDateTime.now())
//                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .error("Internal Server Error")
//                .details("An unexpected error occurred. Please try again later.")
//                .build();
//
//        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}