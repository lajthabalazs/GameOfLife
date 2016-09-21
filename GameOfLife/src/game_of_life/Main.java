package game_of_life;

import game_of_life.model.TorusBoard;
import game_of_life.model.Game;
import game_of_life.model.ConwaysRule;
import game_of_life.view.GameView;

public class Main {
	private static final int DEFAULT_SIZE = 50;
	public static void main(String[] args) {
		int width = DEFAULT_SIZE;
		int height = DEFAULT_SIZE;
		try {
			if (args.length == 1) {
				int l = Integer.parseInt(args[0]);
				width = l;
				height = l;
			} else if (args.length == 2) {
				width = Integer.parseInt(args[0]);
				width = Integer.parseInt(args[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		new GameView(new Game(new TorusBoard(width, height), new ConwaysRule()));
	}
}