package game_of_life.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game_of_life.model.Game;
import game_of_life.model.GenericRule;
import game_of_life.model.Rule;

public final class GameView {
	private final JFrame frame;
	private final GamePanel gamePanel;
	private final Game game;
	private boolean paused = true;
	private JButton playButton;
	Timer timer = new Timer();

	public GameView(Game game) {
		this.game = game;
		frame = new JFrame("Game of Life");
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout());
		final JTextField ruleText = new JTextField("B3/S23");
		buttonPanel.add(ruleText);
		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener( ActionEvent -> { if (!applyRule(ruleText.getText())) {ruleText.setText("");}});
		buttonPanel.add(applyButton);
		JButton stepButton = new JButton("Step");
		stepButton.addActionListener( ActionEvent -> { pause(); step();});
		buttonPanel.add(stepButton);
		playButton = new JButton("Play");
		playButton.addActionListener( ActionEvent -> { togglePlay();});
		buttonPanel.add(playButton);
		buttonPanel.add(stepButton);
		gamePanel = new GamePanel();
		gamePanel.setGame(game);
		gamePanel.setPreferredSize(new Dimension(1000, 1000));
		gamePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX() * game.getBoard().getWidth() / gamePanel.getWidth();
				int y = e.getY() * game.getBoard().getHeight() / gamePanel.getHeight();
				game.getBoard().getCell(x, y).setDead(!game.getBoard().getCell(x, y).isDead());
				gamePanel.repaint();
			}
		});
		mainPanel.add(buttonPanel, BorderLayout.NORTH);
		mainPanel.add(gamePanel, BorderLayout.CENTER);
		frame.setContentPane(mainPanel);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if (!paused) {
					step();
				}
			}
		}, 500, 500);
	}
	
	private boolean applyRule(String ruleString) {
		Rule rule = GenericRule.parse(ruleString);
		if (rule != null) {
			game.applyRule(rule);
			return true;
		}
		return false;
	}

	private void togglePlay() {
		paused = !paused;
		if (paused) {
			playButton.setText("Play");
		} else {
			playButton.setText("Pause");
		}
	}

	private void pause() {
		playButton.setText("Play");
		paused  = true;
	}
	
	private void step() {
		game.step();
		gamePanel.repaint();
	}
}
