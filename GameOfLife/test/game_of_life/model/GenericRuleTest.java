package game_of_life.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class GenericRuleTest {

	@Test
	public void parseValidRule() {
		String[] ruleStrings = {"B/S", "B1/S", "B/S1", "B1234/S467",  "B123456780/S4567"};
		for (String ruleString : ruleStrings) {
			Rule rule = GenericRule.parse(ruleString);
			assertNotNull(rule);
		}
	}
	
	@Test
	public void parseInvalidRule() {
		String[] ruleStrings = {"B/", "/S", "/", "B1234S4567", "B9/S7", "B7/S89", "B11/S123", "B1/S11", "B234/S234/3","B12/Sa","Ba/S12", "c5/a3"};
		for (String ruleString : ruleStrings) {
			Rule rule = GenericRule.parse(ruleString);
			assertNull(rule);
		}
	}

	@Test
	public void birthTest() {
		SquareBoard board = new SquareBoard(10, 10);
		Cell cell = board.getCell(5, 5);
		Rule rule = GenericRule.parse("B1234/S8");
		Cell n1 = board.getCell(4, 5);
		n1.setDead(false);
		Cell n2 = board.getCell(5, 4);
		n2.setDead(false);
		Cell n3 = board.getCell(6, 5);
		n3.setDead(false);
		Cell n4 = board.getCell(5, 6);
		n4.setDead(false);

		rule.apply(cell, board);
		cell.step();
		assertEquals(false, cell.isDead());
	}

	@Test
	public void survivalTest() {
		SquareBoard board = new SquareBoard(10, 10);
		Cell cell = board.getCell(5, 5);
		cell.setDead(false);
		Rule rule = GenericRule.parse("B123/S48");
		Cell n1 = board.getCell(4, 5);
		n1.setDead(false);
		Cell n2 = board.getCell(5, 4);
		n2.setDead(false);
		Cell n3 = board.getCell(6, 5);
		n3.setDead(false);
		Cell n4 = board.getCell(5, 6);
		n4.setDead(false);

		rule.apply(cell, board);
		cell.step();
		assertEquals(false, cell.isDead());
	}

	@Test
	public void notBirthTest() {
		SquareBoard board = new SquareBoard(10, 10);
		Cell cell = board.getCell(5, 5);
		Rule rule = GenericRule.parse("B01245678/S8");
		Cell n1 = board.getCell(4, 5);
		n1.setDead(false);
		Cell n2 = board.getCell(5, 4);
		n2.setDead(false);
		Cell n3 = board.getCell(6, 5);
		n3.setDead(false);

		rule.apply(cell, board);
		cell.step();
		assertEquals(true, cell.isDead());
	}
	
	@Test
	public void deathTest() {
		SquareBoard board = new SquareBoard(10, 10);
		Cell cell = board.getCell(5, 5);
		cell.setDead(false);
		Rule rule = GenericRule.parse("B1234/S12456780");
		Cell n1 = board.getCell(4, 5);
		n1.setDead(false);
		Cell n2 = board.getCell(5, 4);
		n2.setDead(false);
		Cell n3 = board.getCell(6, 5);
		n3.setDead(false);

		rule.apply(cell, board);
		cell.step();
		assertEquals(true, cell.isDead());
	}
}
