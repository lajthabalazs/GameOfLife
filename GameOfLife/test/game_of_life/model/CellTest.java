package game_of_life.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CellTest {
	
	@Test
	public void instantLife() {
		Cell c = new Cell();
		c.setDead(false);
		assertEquals(false, c.isDead());
	}
	
	@Test
	public void live() {
		Cell c = new Cell();
		c.setDead(false);
		c.step();
		assertEquals(false, c.isDead());
	}
	
	@Test
	public void becomeLive() {
		Cell c = new Cell();
		c.becomeLive();
		c.step();
		assertEquals(false, c.isDead());
	}

	@Test
	public void dontBecomeLiveJustYet() {
		Cell c = new Cell();
		c.becomeLive();
		assertEquals(true, c.isDead());
	}
	
	@Test
	public void liveAndDie() {
		Cell c = new Cell();
		c.becomeLive();
		c.step();
		c.die();
		c.step();
		assertEquals(true, c.isDead());
	}
}
