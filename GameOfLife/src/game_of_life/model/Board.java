package game_of_life.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Board {

	private enum Neighbor {
		TOP_LEFT(-1, -1), TOP(0, -1), TOP_RIGHT(1, -1),
		LEFT(-1, 0), RIGHT(1, 0),
		BOTTOM_LEFT(-1, 1), BOTTOM(0, 1), BOTTOM_RIGHT (1, 1);
		private final int xOffset;
		private final int yOffset;

		Neighbor(int xOffset, int yOffset) {
			this.xOffset = xOffset;
			this.yOffset = yOffset;
		}
		
		public boolean isNeighborOnBoard(int x, int y, Board board) {
			return (x + xOffset >= 0 && x + xOffset < board.getWidth()) && (y + yOffset >= 0 && y + yOffset < board.getHeight());
		}

		public int getX(int x) {
			return x + xOffset;
		}

		public int getY(int y) {
			return y + yOffset;
		}
	}
	
	private final int width;
	private final int height;
	private final Cell[][] cells;
	private final HashMap<Cell, int[]> cellCoords = new HashMap<>();
	
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		cells = new Cell[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				cells[i][j] = new Cell();
				cellCoords.put(cells[i][j], new int[]{i,j});
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Set<Cell> getNeighbors(Cell cell) {
		int[] cellCoord = cellCoords.get(cell);
		int x = cellCoord[0];
		int y = cellCoord[1];
		Set<Cell> ret = new HashSet<>();
		for (Neighbor neighbor : Neighbor.values()) {
			if (neighbor.isNeighborOnBoard(x, y, this)) {
				ret.add(cells[neighbor.getX(x)][neighbor.getY(y)]);
			}
		}
		return ret;
	}
	
	public Set<Cell> getCells() {
		return new HashSet<>(cellCoords.keySet());
	}
	
	public Cell getCell(int x, int y) {
		return cells[x][y];
	}
	
	public int[] getCellCoords(Cell cell) {
		return cellCoords.get(cell);
	}
}
