package com.jason798.exception;

/**
 * common exception
 */
public class CommonException extends RuntimeException {
	
	private static final long	serialVersionUID	= 1L;

	public ErrorCode errorCode;

	public CommonException() {
	}

	public CommonException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public CommonException(String message, Throwable cause, ErrorCode errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public CommonException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public CommonException(Throwable cause, ErrorCode errorCode) {
		super(cause);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
}