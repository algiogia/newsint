package com.ivano.rover.service;

import org.apache.commons.lang.StringUtils;

import com.ivano.rover.Direction;
import com.ivano.rover.Plateau;
import com.ivano.rover.Robot;
import com.ivano.rover.exception.InvalidCommandException;
import com.ivano.rover.exception.InvalidParameterException;
import com.ivano.rover.exception.RoverException;

/**
 * Default implementation for {@link Console}
 * 
 * @author ivano.vingiani
 * 
 */
public class DefaultConsole implements Console {

	private final String RESET_COMMAND = "#reset";

	private Plateau plateau;
	private ConsoleStatus status = ConsoleStatus.NEW;
	private Robot robot;

	@Override
	public String execute(String command) throws RoverException {

		if (RESET_COMMAND.equals(command)) {
			status = ConsoleStatus.NEW;
			plateau = null;
			robot = null;
		}
		switch (status) {
		case NEW:
			parseInitCommand(command);
			break;
		case INITIALISED:
			parseRobotDeploymentCommand(command);
			break;
		case ROBOT_DEPLOYED:
			parseRobotInstructionCommand(command);
			String result = robot.getPosition();

			dropRobot();
			return result;
		}

		return null;
	}

	private void dropRobot() {
		robot = null;
		status = ConsoleStatus.INITIALISED;
	}

	private void parseRobotInstructionCommand(String command)
			throws RoverException {
		if (StringUtils.isBlank(command)) {
			throw new InvalidCommandException("Command cannot be empty.");
		}

		for (char com : command.toCharArray()) {
			robot.execute(com);
		}
	}

	private void parseRobotDeploymentCommand(String command)
			throws RoverException {

		String[] parameters = command.split(" ");

		if (parameters.length != 3) {

			throw new InvalidCommandException(
					"Invalid initialisation command. Expected 3 parameters.");
		}

		try {

			int x = Integer.parseInt(parameters[0]);
			int y = Integer.parseInt(parameters[1]);

			Direction direction = Direction.valueOfCode(parameters[2]);

			robot = new Robot(x, y, direction, plateau);

			status = ConsoleStatus.ROBOT_DEPLOYED;
		} catch (NumberFormatException ne) {

			throw new InvalidParameterException(
					"The coordinate parameters in the command are invalid: "
							+ command + ". Expecting two integers.");
		} catch (IllegalArgumentException ie) {

			throw new InvalidParameterException(
					"The direction parameter in the command is invalid: "
							+ command + ". Expecting either N, S, E or W.");
		}

	}

	private void parseInitCommand(String command) throws RoverException {

		String[] parameters = command.split(" ");

		if (parameters.length != 2) {
			throw new InvalidCommandException(
					"Invalid initialisation command. Expected 2 parameters.");
		}

		try {
			int x = Integer.parseInt(parameters[0]);
			int y = Integer.parseInt(parameters[1]);

			plateau = new Plateau(x, y);
			status = ConsoleStatus.INITIALISED;

		} catch (NumberFormatException e) {

			throw new InvalidParameterException(
					"The parameters in the command are invalid: " + command
							+ ". Expecting two integers.");
		}
	}

	private enum ConsoleStatus {

		NEW,

		INITIALISED,

		ROBOT_DEPLOYED
	}

}