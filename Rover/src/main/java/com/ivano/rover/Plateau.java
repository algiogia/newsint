package com.ivano.rover;

/**
 * @author Ivano
 * 
 *         Defines a plateau.
 */
public class Plateau {

	private final int xSize;
	private final int ySize;

	public Plateau(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
	}

	public int getxSize() {
		return xSize;
	}

	public int getySize() {
		return ySize;
	}
}
