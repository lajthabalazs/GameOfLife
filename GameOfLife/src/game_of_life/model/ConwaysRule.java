package game_of_life.model;

public class ConwaysRule implements Rule {
	Rule rule = GenericRule.parse("B3/S23");
	public void apply(Cell cell, CartesianBoard board) {
		rule.apply(cell, board);
	}
}