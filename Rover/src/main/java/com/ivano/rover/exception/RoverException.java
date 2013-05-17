package com.ivano.rover.exception;

public abstract class RoverException extends Exception {

	private static final long serialVersionUID = 1L;

	public RoverException() {
	}

	public RoverException(String msg) {
		super(msg);
	}
}
