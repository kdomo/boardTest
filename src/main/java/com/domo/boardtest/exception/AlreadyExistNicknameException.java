package com.domo.boardtest.exception;

public class AlreadyExistNicknameException extends RuntimeException {
    public AlreadyExistNicknameException(String message) {
        super(message);
    }
}
