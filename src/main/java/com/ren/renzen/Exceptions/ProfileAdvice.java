package com.ren.renzen.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ProfileAdvice {
    @ResponseBody
    @ExceptionHandler(ProfileNotFoundException.class)
    public final ResponseEntity<ProfileNotFoundResponse> profileNotFoundResponseResponseEntity(ProfileNotFoundException ex) {
        ProfileNotFoundResponse response = new ProfileNotFoundResponse((ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
