package org.arpit.java2blog.exception;

public class EliteException extends RuntimeException {
	private static final long serialVersionUID = -8460356990632230194L;

	private final ErrorCode code;

	public EliteException(ErrorCode code) {
		super();
		this.code = code;
	}
	public EliteException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}
	public EliteException(String message, ErrorCode code) {
		super(message);
		this.code = code;
	}
	public EliteException(Throwable cause, ErrorCode code) {
		super(cause);
		this.code = code;
	}
	public ErrorCode getCode() {
		return this.code;
	}


}
