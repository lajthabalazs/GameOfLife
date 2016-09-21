package game_of_life.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public abstract class CartesianBoard {
	private final int width;
	private final int height;
	private final Cell[][] cells;
	private final HashMap<Cell, int[]> cellCoords = new HashMap<>();
	
	public CartesianBoard(int width, int height) {
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

	public final Set<Cell> getNeighbors(Cell cell) {
		int[] cellCoord = cellCoords.get(cell);
		int x = cellCoord[0];
		int y = cellCoord[1];
		Set<Cell> ret = new HashSet<>();
		for (Neighbor neighbor : Neighbor.values()) {
			Cell neighborCell = getNeighbor(x, y, neighbor);
			if (neighborCell != null) {
				ret.add(neighborCell);
			}
		}
		return ret;
	}

	protected abstract Cell getNeighbor(int x, int y, Neighbor neighbor);

	public Set<Cell> getCells() {
		return new HashSet<>(cellCoords.keySet());
	}

	public Cell getCell(int x, int y) {
		return cells[x][y];
	}

	public int[] getCellCoords(Cell cell) {
		return cellCoords.get(cell);
	}

	public static boolean copyCells(CartesianBoard source, CartesianBoard destination) {
		synchronized (source) {
			if (source.getWidth() != destination.getWidth() || source.getHeight() != destination.getHeight()) {
				return false;
			}
			for (int i = 0; i < source.getWidth(); i++) {
				for (int j = 0; j < source.getHeight(); j++) {
					destination.cells[i][j] = source.cells[i][j];
				}
			}
			return true;
		}
	}
}
