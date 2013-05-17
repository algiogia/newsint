package com.ivano.rover;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Direction {

	NORD("N", "W", "E"),

	SOUTH("S", "E", "W"),

	EAST("E", "N", "S"),

	WEST("W", "S", "N");

	private final String code;
	private final String left;
	private final String right;

	private static Map<String, Direction> DIRECTIONS_BY_CODE;

	static {
		Map<String, Direction> m = new HashMap<String, Direction>();
		for (Direction d : Direction.values()) {
			m.put(d.code, d);
		}
		DIRECTIONS_BY_CODE = Collections.unmodifiableMap(m);
	}

	private Direction(String code, String left, String right) {
		this.code = code;
		this.left = left;
		this.right = right;
	}

	/**
	 * @return Direction's code
	 */
	public String getCode() {
		return code;
	}

	public Direction getLeft() {
		return DIRECTIONS_BY_CODE.get(left);
	}

	public Direction getRight() {
		return DIRECTIONS_BY_CODE.get(right);
	}

	/**
	 * Retrieves a Direction given its code.
	 * 
	 * @param code
	 *            direction's code
	 * 
	 * @return the {@link Direction} corresponding to the code
	 * 
	 * @exception IllegalArgumentException
	 *                if given code does not correspond to a {@link Direction}
	 */
	public static Direction valueOfCode(String code)
			throws IllegalArgumentException {
		Direction direction = DIRECTIONS_BY_CODE.get(code);

		if (direction == null) {
			throw new IllegalArgumentException("No such a Direction: " + code);
		}

		return direction;
	}
}
