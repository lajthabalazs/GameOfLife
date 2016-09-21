package game_of_life.model;

import org.junit.Test;

import game_of_life.model.Board;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

public class BoardTest {
	
	@Test
	public void dimensions() {
		final int width = 23;
		final int height = 37;
		Board board = new Board(width, height);
		assertEquals(width, board.getWidth());
		assertEquals(height, board.getHeight());
		assertEquals(width * height, board.getCells().size());
	}
	
	@Test
	public void isInPlace() {
		final int width = 19;
		final int height = 43;
		Board board = new Board(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y ++) {
				Cell cell = board.getCell(x, y);
				assertEquals(x, board.getCellCoords(cell)[0]);
				assertEquals(y, board.getCellCoords(cell)[1]);
			}
		}
	}
	
	@Test
	public void neighborsInTopLeftCorner() {
		final int width = 19;
		final int height = 43;
		Board board = new Board(width, height);
		Set<Cell> expected = new HashSet<>();
		expected.add(board.getCell(1, 0));
		expected.add(board.getCell(0, 1));
		expected.add(board.getCell(1, 1));
		Set<Cell> neighbors = board.getNeighbors(board.getCell(0, 0));
		assertEquals(expected, neighbors);
	}


	@Test
	public void neighborsInTopRightCorner() {
		final int width = 19;
		final int height = 43;
		Board board = new Board(width, height);
		Set<Cell> expected = new HashSet<>();
		expected.add(board.getCell(17, 0));
		expected.add(board.getCell(17, 1));
		expected.add(board.getCell(18, 1));
		Set<Cell> neighbors = board.getNeighbors(board.getCell(18, 0));
		assertEquals(expected, neighbors);
	}
	
	@Test
	public void neighborsInBottomLeftCorner() {
		final int width = 19;
		final int height = 43;
		Board board = new Board(width, height);
		Set<Cell> expected = new HashSet<>();
		expected.add(board.getCell(0, 41));
		expected.add(board.getCell(1, 41));
		expected.add(board.getCell(1, 42));
		Set<Cell> neighbors = board.getNeighbors(board.getCell(0, 42));
		assertEquals(expected, neighbors);
	}


	@Test
	public void neighborsInBottomRightCorner() {
		final int width = 19;
		final int height = 43;
		Board board = new Board(width, height);
		Set<Cell> expected = new HashSet<>();
		expected.add(board.getCell(17, 41));
		expected.add(board.getCell(18, 41));
		expected.add(board.getCell(17, 42));
		Set<Cell> neighbors = board.getNeighbors(board.getCell(18, 42));
		assertEquals(expected, neighbors);
	}
}
