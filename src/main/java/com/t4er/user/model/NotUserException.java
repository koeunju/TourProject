package com.t4er.user.model;


public class NotUserException extends Exception {
	
	public NotUserException() {
		super("NotUserException");
	}
	public NotUserException(String msg) {
		super(msg);
	}

}
