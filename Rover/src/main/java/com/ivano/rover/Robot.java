package com.ivano.rover;

import com.ivano.rover.exception.CoordinatesOutOfBoundsException;
import com.ivano.rover.exception.InvalidCommandException;
import com.ivano.rover.exception.RoverException;

public class Robot {

	private int xCoord;
	private int yCoord;
	private Direction direction;
	private final Plateau plateau;

	/**
	 * Creates a new Robot, initialized according to given parameters.
	 * 
	 * @param xCoord
	 *            starting x coordinate
	 * @param yCoord
	 *            starting y coordinate
	 * @param direction
	 *            starting direction
	 * @throws RoverException
	 */
	public Robot(int xCoord, int yCoord, Direction direction, Plateau plateau)
			throws RoverException {

		if (xCoord > plateau.getxSize()) {
			throw new CoordinatesOutOfBoundsException(
					"x position of robot out of bounds : " + xCoord
							+ ". Must be within 0 and " + plateau.getxSize()
							+ ".");
		}
		if (yCoord > plateau.getySize()) {
			throw new CoordinatesOutOfBoundsException(
					"y position of robot out of bounds : " + yCoord
							+ ". Must be within 0 and " + plateau.getySize()
							+ ".");
		}
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.direction = direction;
		this.plateau = plateau;
	}

	public void execute(char command) throws RoverException {
		execute(String.valueOf(command));
	}

	public void execute(String command) throws RoverException {

		RobotCommand rCom;
		try {
			rCom = RobotCommand.valueOf(command);
		} catch (IllegalArgumentException ie) {
			throw new InvalidCommandException("Unknow command " + command);
		}

		switch (rCom) {
		case L:

			turnLeft();
			break;
		case R:

			turnRight();
			break;
		case M:

			move();
		}
	}

	private void turnLeft() {
		direction = direction.getLeft();
	}

	private void turnRight() {
		direction = direction.getRight();
	}

	private void move() throws RoverException {

		boolean invalidMove = false;
		switch (direction) {
		case NORD:

			if (yCoord + 1 > plateau.getySize()) {
				invalidMove = true;
			} else {
				yCoord++;
			}
			break;
		case EAST:

			if (xCoord + 1 > plateau.getxSize()) {
				invalidMove = true;
			} else {
				xCoord++;
			}
			break;
		case SOUTH:

			if (yCoord - 1 < 0) {
				invalidMove = true;
			} else {
				yCoord--;
			}
			break;
		case WEST:

			if (xCoord - 1 < 0) {
				invalidMove = true;
			} else {
				xCoord--;
			}
			break;
		}

		if (invalidMove) {
			throw new CoordinatesOutOfBoundsException("Trying to move robot "
					+ direction.getCode() + " from (" + xCoord + ", " + yCoord
					+ ")");
		}
	}

	public String getPosition() {

		return "" + xCoord + " " + yCoord + " " + direction.getCode();
	}

	private enum RobotCommand {

		L,

		R,

		M
	}
}
