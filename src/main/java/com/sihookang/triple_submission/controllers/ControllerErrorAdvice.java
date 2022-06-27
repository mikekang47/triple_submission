package com.sihookang.triple_submission.controllers;

import com.sihookang.triple_submission.dto.ErrorResponse;
import com.sihookang.triple_submission.errors.InvalidTypeException;
import com.sihookang.triple_submission.errors.PlaceNotFoundException;
import com.sihookang.triple_submission.errors.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseBody
@ControllerAdvice
public class ControllerErrorAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidTypeException.class)
    public ErrorResponse handleInvalidTypeException() {
        return new ErrorResponse("Invalid Type");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleUserNotFoundException() {
        return new ErrorResponse("User not found");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PlaceNotFoundException.class)
    public ErrorResponse handlePlaceNotFoundException() {
        return new ErrorResponse("Place not found");
    }

}
