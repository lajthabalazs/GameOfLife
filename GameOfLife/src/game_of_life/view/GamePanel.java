package game_of_life.view;

import java.awt.Graphics;
import java.util.Set;

import javax.swing.JPanel;

import game_of_life.model.Cell;
import game_of_life.model.Game;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -7385152825995539836L;

	private Game game;

	public void setGame(Game game) {
		this.game = game;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, getWidth(), getHeight());
		Set<Cell> cells = game.getBoard().getCells();
		for (Cell cell : cells) {
			if (!cell.isDead()) {
				int[] coords = game.getBoard().getCellCoords(cell);
				g.fillRect(coords[0] * getWidth() / game.getBoard().getWidth(),
						coords[1] * getHeight() / game.getBoard().getHeight(),
						getWidth() / game.getBoard().getWidth(), 
						getHeight() / game.getBoard().getHeight());
			}
		}
	}

}
