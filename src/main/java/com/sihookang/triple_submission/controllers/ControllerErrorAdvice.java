package com.sihookang.triple_submission.controllers;

import com.sihookang.triple_submission.dto.ErrorResponse;
import com.sihookang.triple_submission.errors.InvalidTypeException;
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

}
