package com.chpt.test.exception;

public class GameBoardNotInitializedException extends Exception{

	private static final long serialVersionUID = 1L;

	public GameBoardNotInitializedException(String message) {
		super(message);
	}
}
