package game_of_life.model;

public class TorusBoard extends CartesianBoard{
	
	public enum Orientation {
		HORIZONTAL(true, false), VERTICAL(false, true), BOTH(true, true);
		private boolean horizontal;
		private boolean vertical;

		Orientation(boolean horizontal, boolean vertical) {
			this.horizontal = horizontal;
			this.vertical = vertical;
		}
	}

	private Orientation orientation;

	public TorusBoard(int width, int height) {
		this(width, height, Orientation.BOTH);
	}
	
	public TorusBoard(int width, int height, Orientation orientation) {
		super(width, height);
		this.orientation = orientation;
	}

	@Override
	protected Cell getNeighbor(int x, int y, Neighbor neighbor) {
		if (!orientation.horizontal && (x + neighbor.xOffset < 0 || x + neighbor.xOffset > getWidth() - 1)) {
			return null;
		}
		if (!orientation.vertical && (y + neighbor.yOffset < 0 || y + neighbor.yOffset > getHeight() - 1)) {
			return null;
		}
		return getCell((x + neighbor.xOffset + getWidth()) % getWidth(), (y + neighbor.yOffset  + getHeight()) % getHeight());
	}
}