package com.ivano.rover.exception;

public class CoordinatesOutOfBoundsException extends RoverException {

	private static final long serialVersionUID = 1L;

	public CoordinatesOutOfBoundsException(String msg) {
		super(msg);
	}
}
