package game_of_life.model;

public class Cell {
	private boolean dead = true;
	private boolean nextState = true;
	private boolean shouldChangeState = false;
	
	public boolean isDead() {
		return dead;
	}
	
	public void die() {
		nextState = true;
		shouldChangeState = true;
	}
	
	public void becomeLive() {
		nextState = false;
		shouldChangeState = true;
	}
	
	public void step() {
		if (shouldChangeState) {
			dead = nextState;
		}
		shouldChangeState = false;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
}
