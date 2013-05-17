package com.ivano.rover;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ivano.rover.exception.CoordinatesOutOfBoundsException;
import com.ivano.rover.exception.InvalidCommandException;
import com.ivano.rover.exception.InvalidParameterException;
import com.ivano.rover.service.Console;
import com.ivano.rover.service.DefaultConsole;

public class ConsoleTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void firstCommandShouldInitialisePlateau() throws Throwable {

		Console console = new DefaultConsole();

		assertEquals(null, console.execute("5 5"));
	}

	@Test
	public void aRobotMovesInThePlateau() throws Throwable {

		Console console = new DefaultConsole();
		// init Plateau
		console.execute("5 5");

		// deploy robot
		console.execute("1 2 N");

		// move robot
		String result = console.execute("LMLMLMLMM");

		assertEquals("1 3 N", result);
	}

	@Test
	public void anExceptionIsThrownIfRobotMovesOutsideThePlateau()
			throws Throwable {

		Console console = new DefaultConsole();
		// init Plateau
		console.execute("5 5");

		// deploy robot
		console.execute("1 2 N");

		// move robot
		exception.expect(CoordinatesOutOfBoundsException.class);
		String result = console.execute("RMMLMLMM");

		assertEquals("0 2 E", result);
	}

	@Test
	public void anExceptionIsThrownIfCommandIsInvalid() throws Throwable {

		Console console = new DefaultConsole();

		exception.expect(InvalidCommandException.class);
		console.execute("");

		exception.expect(InvalidCommandException.class);
		console.execute("a");
	}

	@Test
	public void anExceptionIsThrownIfInitialisationParametersAreWrong()
			throws Throwable {

		Console console = new DefaultConsole();

		exception.expect(InvalidParameterException.class);
		console.execute("5 a");
	}

	@Test
	public void anExceptionIsThrownIfDeploymentParametersAreWrong()
			throws Throwable {

		Console console = new DefaultConsole();

		console.execute("5 5");

		exception.expect(InvalidParameterException.class);
		console.execute("a b d");
	}
}
