package snake.computer_player;

import snake.SnakeMain;
import snake.SnakeView;

public class ComputerPlayerSnakeMain extends SnakeMain {

	/*
	 * public ComputerPlayerSnakeMain() { setTitle("Snake");
	 * setLocationRelativeTo(null); JPanel panel = new JPanel();
	 * setSize(ComputerPlayerSnakeView.SIDELENGTH,
	 * ComputerPlayerSnakeView.SIDELENGTH);
	 * setDefaultCloseOperation(EXIT_ON_CLOSE); panel.add(new
	 * ComputerPlayerSnakeView( new ComputerPlayerGameController()));
	 * panel.setPreferredSize(new Dimension( ComputerPlayerSnakeView.SIDELENGTH,
	 * ComputerPlayerSnakeView.SIDELENGTH)); add(panel); pack();
	 * setVisible(true); setResizable(false); }
	 */

	public ComputerPlayerSnakeMain() {
		super();
		panel.add(new SnakeView(new ComputerPlayerGameController()));
		showMain();
	}

	public static void main(String[] args) {
		new ComputerPlayerSnakeMain();
	}

}
