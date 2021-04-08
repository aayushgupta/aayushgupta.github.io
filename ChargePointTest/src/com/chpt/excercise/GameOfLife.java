package com.chpt.excercise;

import com.chpt.test.exception.GameBoardNotInitializedException;

/**
 * The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells, 
 * each of which is in one of two possible states, alive or dead. Every cell interacts with its eight neighbors, 
 * which are the cells that are horizontally, vertically, or diagonally adjacent. 
 * 
 * 
 * @author aygupta
 *
 */
public class GameOfLife {

	private int[][] universe;
	private int rows;
	private int cols;
	
	private int[][] neighbors = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
			
	public GameOfLife(int[][] universe) throws GameBoardNotInitializedException {
		
		if(universe == null) {
			throw new GameBoardNotInitializedException("Game Board cannot be created without seed universe.");
		}
		this.universe = universe;
		this.rows = universe.length;
		this.cols = universe[0].length;
		
	}
	
	/**
	 * Create the new  generation based on the transition rules.
	 */
	public void createGeneration() {

		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.cols; col++) {

				int adjacentAliveCellCount = adjacentAliveCellCount(row, col);

				this.universe[row][col] = determineNewCellState(adjacentAliveCellCount, row, col);
			}
		}

		for (int row = 0; row < this.rows; row++) {
			for (int col = 0; col < this.cols; col++) {
				if (this.universe[row][col] > 0) {
					this.universe[row][col] = 1;
				} else {
					this.universe[row][col] = 0;
				}
			}
		}
	}
	
	/**
	 * Determine the current state of cell based on the alive adjacent neighbors in 8 directions.
	 * 
	 *  Rules to determine the cell state
	 *  1. Any live cell with fewer than two live neighbors dies as if caused by underpopulation.
	 *	2. Any live cell with two or three live neighbors lives on to the next generation.
	 *	3. Any live cell with more than three live neighbors dies, as if by overcrowding.
	 *	4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
	 * 
	 * @param currentState
	 * @param aliveNeighbors
	 * @param row
	 * @param col
	 * @return
	 * 
	 * 0  - signifies the cell was originally dead and remains dead.
	 * 1  - signifies the cell was originally live and remains alive.
	 * -1 - signifies the cell is currently dead but originally was live.
	 * 2  - signifies the cell is currently alive but originally was dead.
	 * 
	 */
	private int determineNewCellState(int aliveNeighbors, int row, int col) {
		
        // Rule 1 or Rule 3
        if ((this.universe[row][col] == 1) && (aliveNeighbors < 2 || aliveNeighbors > 3)) {
        	return -1;
        }
        // Rule 4
        if (this.universe[row][col] == 0 && aliveNeighbors == 3) {
        	return 2;
        }
		return this.universe[row][col];
	}
	
	/**
	 * Determine the count of alive neighbors in adjacent 8 directions.
	 * @param ri
	 * @param ci
	 * @return
	 */
	private int adjacentAliveCellCount(int ri, int ci) {
		
		int adjacentAliveCellCount = 0;
		for(int[] neighbor : this.neighbors) {
			
			int r = ri + neighbor[0];
			int c = ci + neighbor[1];
			
			if((r < this.rows && r >= 0) && (c < this.cols && c >=0) && Math.abs(this.universe[r][c]) == 1) {
				
				adjacentAliveCellCount++;
			}
		}
		return adjacentAliveCellCount;
	}
	
	
	/**
	 * Prints the current state of universe on the console. 
	 */
	public void drawUniverse() {
		
		if(this.universe != null) {
			
			for(int ri = 0; ri < this.rows; ri++) {
				
				for(int ci = 0; ci < this.cols; ci++) {
					
					System.out.printf("%3d",this.universe[ri][ci]);
				}
				System.out.println("");
			}
		}
	}
}
