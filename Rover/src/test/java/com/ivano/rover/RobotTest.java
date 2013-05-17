package com.ivano.rover;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ivano.rover.exception.CoordinatesOutOfBoundsException;
import com.ivano.rover.exception.InvalidCommandException;

public class RobotTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	private Plateau plateau = mock(Plateau.class);

	@Test
	public void aRobotIsDeployedInsidePlateau() throws Throwable {

		when(plateau.getxSize()).thenReturn(10);
		when(plateau.getySize()).thenReturn(10);

		Robot robot = new Robot(0, 0, Direction.NORD, plateau);

		assertEquals("0 0 N", robot.getPosition());
	}

	@Test
	public void anExceptionIsThrownIfRobotReceivesUnknownCommand()
			throws Throwable {

		when(plateau.getxSize()).thenReturn(5);
		when(plateau.getySize()).thenReturn(5);

		Robot robot = new Robot(0, 0, Direction.NORD, plateau);

		exception.expect(InvalidCommandException.class);
		robot.execute("X");
	}

	@Test
	public void anExceptionIsThrownIfRobotIsDeployedOutsidePlateau()
			throws Throwable {

		when(plateau.getxSize()).thenReturn(5);
		when(plateau.getySize()).thenReturn(1);

		exception.expect(CoordinatesOutOfBoundsException.class);
		new Robot(5, 2, Direction.NORD, plateau);
	}

	@Test
	public void aRobotIsMovedInsideThePlateau() throws Throwable {

		when(plateau.getxSize()).thenReturn(5);
		when(plateau.getySize()).thenReturn(5);

		Robot robot = new Robot(0, 0, Direction.NORD, plateau);
		robot.execute("M");

		assertEquals("0 1 N", robot.getPosition());
	}

	@Test
	public void aRobotTurnsLeftThenMove() throws Throwable {

		when(plateau.getxSize()).thenReturn(5);
		when(plateau.getySize()).thenReturn(5);

		Robot robot = new Robot(0, 0, Direction.NORD, plateau);
		robot.execute("L");
		robot.execute("M");

		assertEquals("1 0 W", robot.getPosition());
	}

	@Test
	public void anExceptionIsThrownIfRobotIsMovedOutsidePlateau()
			throws Throwable {

		when(plateau.getxSize()).thenReturn(5);
		when(plateau.getySize()).thenReturn(5);

		Robot robot = new Robot(5, 5, Direction.NORD, plateau);

		exception.expect(CoordinatesOutOfBoundsException.class);
		robot.execute("M");
	}
}
