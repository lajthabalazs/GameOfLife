package game_of_life.model;

public interface Rule {
	public void apply(Cell cell, Board board);
}