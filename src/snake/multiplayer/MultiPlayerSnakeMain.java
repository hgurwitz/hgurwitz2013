package snake.multiplayer;

import snake.SnakeMain;
import snake.SnakeView;

public class MultiPlayerSnakeMain extends SnakeMain {

	private static final long serialVersionUID = 1L;

	/*
	 * public MultiPlayerSnakeMain() { setTitle("Snake");
	 * setLocationRelativeTo(null); JPanel panel = new JPanel();
	 * setSize(MultiPlayerSnakeView.SIDELENGTH,
	 * MultiPlayerSnakeView.SIDELENGTH);
	 * setDefaultCloseOperation(EXIT_ON_CLOSE); panel.add(new
	 * MultiPlayerSnakeView(new MultiPlayerGameController()));
	 * panel.setPreferredSize(new Dimension(MultiPlayerSnakeView.SIDELENGTH,
	 * MultiPlayerSnakeView.SIDELENGTH)); add(panel); pack(); setVisible(true);
	 * setResizable(false); }
	 */

	public MultiPlayerSnakeMain() {
		super();
		panel.add(new SnakeView(new MultiPlayerGameController()));
		showMain();
	}

	public static void main(String[] args) {
		new MultiPlayerSnakeMain();
	}

}
