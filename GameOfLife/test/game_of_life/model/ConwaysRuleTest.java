package game_of_life.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ConwaysRuleTest {

	@Test
	public void killSingleCell() {
		Board board = new Board(1, 1);
		Cell cell = board.getCell(0, 0);
		cell.setDead(false);
		ConwaysRule rule = new ConwaysRule();
		rule.apply(cell, board);
		cell.step();
		assertEquals(true, cell.isDead());
	}

	@Test
	public void killSingleCellInLargeField() {
		Board board = new Board(10, 10);
		Cell cell = board.getCell(5, 5);
		cell.setDead(false);
		ConwaysRule rule = new ConwaysRule();
		rule.apply(cell, board);
		cell.step();
		assertEquals(true, cell.isDead());
	}

	@Test
	public void killCellWithSingleNeighbor() {
		Board board = new Board(10, 10);
		Cell cell = board.getCell(5, 5);
		Cell neighbor = board.getCell(4, 5);
		cell.setDead(false);
		neighbor.setDead(false);
		ConwaysRule rule = new ConwaysRule();
		rule.apply(cell, board);
		cell.step();
		assertEquals(true, cell.isDead());
	}

	@Test
	public void killCellWithFourNeighbors() {
		Board board = new Board(10, 10);
		Cell cell = board.getCell(5, 5);
		cell.setDead(false);

		Cell n1 = board.getCell(4, 5);
		n1.setDead(false);
		Cell n2 = board.getCell(5, 4);
		n2.setDead(false);
		Cell n3 = board.getCell(6, 5);
		n3.setDead(false);
		Cell n4 = board.getCell(5, 6);
		n4.setDead(false);
		
		ConwaysRule rule = new ConwaysRule();
		rule.apply(cell, board);
		cell.step();
		assertEquals(true, cell.isDead());
	}
	
	@Test
	public void keepCellWithFourNeighborsDead() {
		Board board = new Board(10, 10);
		Cell cell = board.getCell(5, 5);

		Cell n1 = board.getCell(4, 5);
		n1.setDead(false);
		Cell n2 = board.getCell(5, 4);
		n2.setDead(false);
		Cell n3 = board.getCell(6, 5);
		n3.setDead(false);
		Cell n4 = board.getCell(5, 6);
		n4.setDead(false);
		
		ConwaysRule rule = new ConwaysRule();
		rule.apply(cell, board);
		cell.step();
		assertEquals(true, cell.isDead());
	}
	
	@Test
	public void bringCellToLife() {
		Board board = new Board(10, 10);
		Cell cell = board.getCell(5, 5);

		Cell n1 = board.getCell(4, 5);
		n1.setDead(false);
		Cell n2 = board.getCell(5, 4);
		n2.setDead(false);
		Cell n3 = board.getCell(6, 5);
		n3.setDead(false);
		
		ConwaysRule rule = new ConwaysRule();
		rule.apply(cell, board);
		cell.step();
		assertEquals(false, cell.isDead());
	}
	
	@Test
	public void keepCellWithTwoNeighborsAlive() {
		Board board = new Board(10, 10);
		Cell cell = board.getCell(5, 5);
		cell.setDead(false);
		
		Cell n1 = board.getCell(4, 5);
		n1.setDead(false);
		Cell n2 = board.getCell(5, 4);
		n2.setDead(false);
		
		ConwaysRule rule = new ConwaysRule();
		rule.apply(cell, board);
		cell.step();
		assertEquals(false, cell.isDead());
	}
	
	@Test
	public void keepCellWithThreeNeighborsAlive() {
		Board board = new Board(10, 10);
		Cell cell = board.getCell(5, 5);
		cell.setDead(false);
		
		Cell n1 = board.getCell(4, 5);
		n1.setDead(false);
		Cell n2 = board.getCell(5, 4);
		n2.setDead(false);
		Cell n3 = board.getCell(6, 5);
		n3.setDead(false);
		
		ConwaysRule rule = new ConwaysRule();
		rule.apply(cell, board);
		cell.step();
		assertEquals(false, cell.isDead());
	}
}
