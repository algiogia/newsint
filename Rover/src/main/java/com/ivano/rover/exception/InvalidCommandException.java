package com.ivano.rover.exception;

public class InvalidCommandException extends RoverException {

	private static final long serialVersionUID = 1L;

	public InvalidCommandException(String msg) {
		super(msg);
	}
}
