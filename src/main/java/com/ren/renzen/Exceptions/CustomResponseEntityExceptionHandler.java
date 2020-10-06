package com.ren.renzen.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler {

    @ExceptionHandler(ProfileNotFoundException.class)
    public final ResponseEntity<ProfileNotFoundResponse> profileNotFoundResponseResponseEntity(ProfileNotFoundException ex) {
        ProfileNotFoundResponse response = new ProfileNotFoundResponse((ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<UserNameAlreadyExistsResponse> handleUserNameAlreadyExistsException(UserNameAlreadyExistsException ex) {
        UserNameAlreadyExistsResponse response = new UserNameAlreadyExistsResponse((ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
