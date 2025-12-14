package com.examly.springapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<?> userExistHandler(UserExistException userExistException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
    }

    @ExceptionHandler(FeedbackNotFoundException.class)
    public ResponseEntity<?> feedbackNotFoundExcep(FeedbackNotFoundException feedbackNotFoundException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @ExceptionHandler(ProjectAlreadyExistsException.class)
    public ResponseEntity<?> projectExistsExcep(ProjectAlreadyExistsException projectAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<?> projectNotFoundExcep(ProjectNotFoundException projectNotFoundException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @ExceptionHandler(ProjectProposalNotFoundException.class)
    public ResponseEntity<?> projectProposalNotFoundExcep(
            ProjectProposalNotFoundException projectProposalNotFoundException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> userAlreadyExistsExcep(UserAlreadyExistsException userAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundExcep(UserNotFoundException userAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException ex) {
        System.out.println("Handling User not found exception");
        String errorMsg = "Invalid Credentials!! Authentication Failed";
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMsg);
    }
}
