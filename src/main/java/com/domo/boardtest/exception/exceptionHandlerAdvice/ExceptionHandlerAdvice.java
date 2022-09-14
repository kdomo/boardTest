package com.domo.boardtest.exception.exceptionHandlerAdvice;

import com.domo.boardtest.exception.AlreadyExistNicknameException;
import com.domo.boardtest.exception.ErrorMsg;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.domo.boardTest.controller")
public class ExceptionHandlerAdvice {

    private static String getSimpleName(Exception e) {
        return e.getClass().getSimpleName();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExistNicknameException.class)
    public ErrorMsg alreadyExistEmailException(AlreadyExistNicknameException e) {

        return new ErrorMsg(e.getLocalizedMessage(), getSimpleName(e));

    }
}
