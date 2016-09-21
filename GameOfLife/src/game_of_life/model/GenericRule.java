package game_of_life.model;

import java.util.HashSet;
import java.util.Set;

public class GenericRule implements Rule{

	final Set<Integer> birthNumbers = new HashSet<>();
	final Set<Integer> surviveNumbers = new HashSet<>();

	private GenericRule(Set<Integer> birthNumbers, Set<Integer> surviveNumbers) {
		this.birthNumbers.addAll(birthNumbers);
		this.surviveNumbers.addAll(surviveNumbers);
	}

	public static Rule parse(String ruleString) {
		String[] parts = ruleString.split("/");
		if (parts.length != 2 || !parts[0].toLowerCase().startsWith("b")|| !parts[1].toLowerCase().startsWith("s")) {
			return null;
		}
		Set<Integer> birthNumbers = new HashSet<>();
		Set<Integer> surviveNumbers = new HashSet<>();
		for (int i = 1; i < parts[0].length(); i++) {
			try {
				int number = Integer.parseInt(parts[0].substring(i, i + 1));
				if (birthNumbers.contains(number)) {
					return null;
				}
				if (number == 9) {
					return null;
				}
				birthNumbers.add(number);
			} catch (Exception e){
				return null;
			}
		}
		for (int i = 1; i < parts[1].length(); i++) {
			try {
				int number = Integer.parseInt(parts[1].substring(i, i + 1));
				if (surviveNumbers.contains(number)) {
					return null;
				}
				if (number == 9) {
					return null;
				}
				surviveNumbers.add(number);
			} catch (Exception e){
				return null;
			}
		}
		return new GenericRule(birthNumbers, surviveNumbers);
	}

	public void apply(Cell cell, CartesianBoard board) {
		Set<Cell> neighbors = board.getNeighbors(cell);
		int living = 0;
		for (Cell neighbor : neighbors) {
			if (!neighbor.isDead()) {
				living ++;
			}
		}
		if (cell.isDead() && birthNumbers.contains(living)) {
			cell.becomeLive();
		} else if (!cell.isDead() && !surviveNumbers.contains(living)) {
			cell.die();
		}
	}
}