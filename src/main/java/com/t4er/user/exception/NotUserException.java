package com.t4er.user.exception;

public class NotUserException extends Exception {

    public NotUserException() {
        super("NotUserException");
    }

    public NotUserException(String msg) {
        super(msg);
    }
}
