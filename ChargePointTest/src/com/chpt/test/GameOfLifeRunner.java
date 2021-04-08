package com.chpt.test;

import com.chpt.test.exception.GameBoardNotInitializedException;

/**
 * Runner class helps run the Game of Life with initial seed stage, and allows to run as many generations as we want. 
 * @author aygupta
 *
 */
public class GameOfLifeRunner {

	
	public static void main(String[] args) throws GameBoardNotInitializedException {

		int[][] seed = new int[25][25];

		/**
		 * Glider pattern (Game of Life) - Gliders are the smallest spaceships, and they
		 * travel diagonally at a speed of one cell every four generations, or c/4.
		 * https://en.wikipedia.org/wiki/Glider_(Conway%27s_Life)
		 */

		// 1 signifies the cell is alive.
		// 0 signifies the cell is dead.
		seed[10][12] = 1;
		seed[11][13] = 1;
		seed[12][11] = 1;
		seed[12][12] = 1;
		seed[12][13] = 1;

		GameOfLife gol = new GameOfLife(seed);
		System.out.println("*********Seed of the Universe*******");
		gol.drawUniverse();
		System.out.println("");
		int generationCount = 5;
		for(int cnt =0 ; cnt < generationCount; cnt++) {
			gol.createGeneration();
			System.out.println("*********State of Universe after Generation : "+cnt+"   *******");
			gol.drawUniverse();
			System.out.println("");
		}
		
//		
//		GameOfLife gol1 = new GameOfLife(null);
//		gol1.createGeneration();
	}
}
