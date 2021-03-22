package user.model;

public class NotUserException extends Exception {
	public NotUserException() {
		super("NotUserException");
	}
	public NotUserException(String msg) {
		super(msg);
	}
}
