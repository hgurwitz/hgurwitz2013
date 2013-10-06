package snake;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import snake.computer_player.ComputerPlayerGameController;
import snake.multiplayer.MultiPlayerGameController;
import snake.single_player.SinglePlayerGameController;

public class SnakeMain extends JFrame {

	protected static final long serialVersionUID = 1L;
	protected JPanel panel;

	public SnakeMain(GameType type) {
		setTitle("Snake");
		setLocationRelativeTo(null);
		setSize(SnakeView.SIDELENGTH, SnakeView.SIDELENGTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = new JPanel();
		switch (type) {
		case SINGLE_PLAYER:
			panel.add(new SnakeView(new SinglePlayerGameController()));
			break;
		case MULTI_PLAYER:
			panel.add(new SnakeView(new MultiPlayerGameController()));
			break;
		case COMPUTER_PLAYER:
			panel.add(new SnakeView(new ComputerPlayerGameController()));
			break;
		}
		panel.setPreferredSize(new Dimension(SnakeView.SIDELENGTH,
				SnakeView.SIDELENGTH));
		add(panel);
		pack();
		setVisible(true);
		setResizable(false);

	}

}
