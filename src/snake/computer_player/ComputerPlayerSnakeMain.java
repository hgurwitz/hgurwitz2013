package snake.computer_player;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ComputerPlayerSnakeMain extends JFrame {

	private static final long serialVersionUID = 1L;

	public ComputerPlayerSnakeMain() {
		setTitle("Snake");
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		setSize(ComputerPlayerSnakeView.SIDELENGTH, ComputerPlayerSnakeView.SIDELENGTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.add(new ComputerPlayerSnakeView(new ComputerPlayerGameController()));
		panel.setPreferredSize(new Dimension(ComputerPlayerSnakeView.SIDELENGTH,
				ComputerPlayerSnakeView.SIDELENGTH));
		add(panel);
		pack();
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new ComputerPlayerSnakeMain();
	}

}
