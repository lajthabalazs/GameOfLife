package game_of_life.model;

enum Neighbor {
	TOP_LEFT(-1, -1), TOP(0, -1), TOP_RIGHT(1, -1),
	LEFT(-1, 0), RIGHT(1, 0),
	BOTTOM_LEFT(-1, 1), BOTTOM(0, 1), BOTTOM_RIGHT (1, 1);
	protected final int xOffset;
	protected final int yOffset;

	Neighbor(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}