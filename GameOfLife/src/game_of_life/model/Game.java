package game_of_life.model;

import java.util.Set;

public class Game {
	private Board board;
	private Rule rule;

	public Game(Board board, Rule rule) {
		this.board = board;
		this.rule = rule;
	}
	
	public void step() {
		Set<Cell> cells = board.getCells();
		for (Cell cell : cells){
			rule.apply(cell, board);
		}
		for(Cell cell : cells){
			cell.step();
		}
	}
	
	public Board getBoard() {
		return board;
	}

	public void applyRule(Rule rule) {
		this.rule = rule;
	}
}
