package com.ivano.rover.service;

import com.ivano.rover.exception.RoverException;

/**
 * Interface for a command console.
 * 
 * @author ivano.vingiani
 * 
 */
public interface Console {

	public String execute(String command) throws RoverException;
}
