package game_of_life.model;

public class SquareBoard extends CartesianBoard{

	public SquareBoard(int width, int height) {
		super(width, height);
	}

	@Override
	protected Cell getNeighbor(int x, int y, Neighbor neighbor) {
		if (x + neighbor.xOffset >= 0 && x + neighbor.xOffset < getWidth() && y + neighbor.yOffset >= 0 && y + neighbor.yOffset < getHeight()) {
			return getCell(x + neighbor.xOffset, y + neighbor.yOffset);
		} else {
			 return null;
		}
	}
}
