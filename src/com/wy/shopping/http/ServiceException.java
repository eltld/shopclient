package com.wy.shopping.http;


public class ServiceException extends RuntimeException{

	private static final long serialVersionUID = -7845246642818500797L;

    public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
	
}

