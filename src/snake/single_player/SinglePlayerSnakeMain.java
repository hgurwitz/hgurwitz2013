package snake.single_player;

import snake.SnakeMain;
import snake.SnakeView;

public class SinglePlayerSnakeMain extends SnakeMain {

	/*
	 * public SinglePlayerSnakeMain() { setTitle("Snake");
	 * setLocationRelativeTo(null); JPanel panel = new JPanel();
	 * setSize(SinglePlayerSnakeView.SIDELENGTH,
	 * SinglePlayerSnakeView.SIDELENGTH);
	 * setDefaultCloseOperation(EXIT_ON_CLOSE); panel.add(new
	 * SinglePlayerSnakeView(new SinglePlayerGameController()));
	 * panel.setPreferredSize(new Dimension(SinglePlayerSnakeView.SIDELENGTH,
	 * SinglePlayerSnakeView.SIDELENGTH)); add(panel); pack(); setVisible(true);
	 * setResizable(false); }
	 */

	public SinglePlayerSnakeMain() {
		super();
		panel.add(new SnakeView(new SinglePlayerGameController()));
		showMain();
	}

	public static void main(String[] args) {
		new SinglePlayerSnakeMain();
	}

}
