package com.sihookang.triple_submission.controllers;

import com.sihookang.triple_submission.dto.ErrorResponse;
import com.sihookang.triple_submission.errors.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 *  에러와 관련된 HTTP 응답을 관리하는 클래스입니다.
 */
@ResponseBody
@ControllerAdvice
public class ControllerErrorAdvice {
    /**
     * InvalidTypeException이 발생했을 때 client에 에러코드 400을 반환하고 에러 메시지를 반환합니다.
     * @return ErrorReponse 객체
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidTypeException.class)
    public ErrorResponse handleInvalidTypeException() {
        return new ErrorResponse("Invalid Type");
    }

    /**
     * UserNotFoundException이 발생했을 때 client에 에러코드 404를 반환하고 에러 메시지를 반환합니다.
     * @return ErrorReponse 객체
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleUserNotFoundException() {
        return new ErrorResponse("User not found");
    }

    /**
     * PlaceNotFoundException이 발생했을 때 client에 에러코드 404를 반환하고 에러 메시지를 반환합니다.
     * @return ErrorReponse 객체
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PlaceNotFoundException.class)
    public ErrorResponse handlePlaceNotFoundException() {
        return new ErrorResponse("Place not found");
    }

    /**
     * PhotoNotFoundException이 발생했을 때 client에 에러코드 404를 반환하고 에러 메시지를 반환합니다.
     * @return ErrorReponse 객체
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PhotoNotFoundException.class)
    public ErrorResponse handlePhotoNotFoundException() {
        return new ErrorResponse("Photo not found");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReviewNotFoundException.class)
    public ErrorResponse handleReviewNotFoundException() {
        return new ErrorResponse("Review not found");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ActionNotFoundException.class)
    public ErrorResponse handleActionNotFoundException() {
        return new ErrorResponse("Action not found");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IdNotMatchException.class)
    public ErrorResponse handleIdNotMatchException() {
        return new ErrorResponse("Id Not Match");
    }

}
